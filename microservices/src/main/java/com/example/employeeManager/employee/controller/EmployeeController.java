package com.example.employeeManager.employee.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.common.CommonUtil;
import com.example.common.Constants;
import com.example.common.DataTableResults;
import com.example.common.Response;
import com.example.controller.BaseController;
import com.example.email.model.UserEmail;
import com.example.email.service.MailService;
import com.example.employeeManager.employee.bean.EmployeeBean;
import com.example.employeeManager.employee.bo.EmployeeBO;
import com.example.employeeManager.employee.form.EmployeeForm;
import com.example.employeeManager.employee.service.EmployeeService;
import com.example.employeeManager.employeeImages.bo.EmployeeImagesBO;
import com.example.employeeManager.employeeImages.service.EmployeeImagesService;
import com.example.genCodeEmployee.bo.GenCodeEmployeeBO;
import com.example.genCodeEmployee.service.GenCodeEmployeeService;
import com.example.timekeeping.bo.TimekeepingBO;
import com.example.timekeeping.service.TimekeepingService;
import com.example.user.entity.RoleBO;
import com.example.user.entity.UserBO;
import com.example.user.entity.UserForm;
import com.example.user.entity.UserRoleBO;
import com.example.user.service.RoleService;
import com.example.user.service.UserRoleService;
import com.example.user.service.UserService;

@Controller
@RequestMapping("/v1/employee-manager/employee")
public class EmployeeController extends BaseController {

    private static String adResourceKey = "resource.employee";

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeImagesService employeeImagesService;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private UserRoleService userRoleService;
    
    @Autowired
    private GenCodeEmployeeService genCodeEmployeeService;
    
    @Autowired
    private TimekeepingService timekeepingService;

    /**
     * findById
     * 
     * @param employeeId
     * @return
     */
    @GetMapping(path = "/{employeeId}")
    public @ResponseBody Response findById(HttpServletRequest req, @PathVariable Long employeeId) {
//        if (! permissionChecker.hasPermission("action.view", adResourceKey, req)) {
//            return Response.invalidPermission();
//        }
        EmployeeBO employeeBO = employeeService.findById(employeeId);
        if (employeeBO == null) {
            return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
        }
        if (employeeImagesService.getEmployeeImageByEmployeeId(employeeId) != null) {
            employeeBO.setEmployeeImgUrl(
                    employeeImagesService.getEmployeeImageByEmployeeId(employeeId).getEmployeeImgUrl());
        }
        return Response.success().withData(employeeBO);
    }

    /**
     * processSearch
     * 
     * @param EmployeeForm form
     * @return DataTableResults
     */
    @GetMapping(path = "/search")
    public @ResponseBody DataTableResults<EmployeeBean> processSearch(HttpServletRequest req, EmployeeForm form) {
//        if (! permissionChecker.hasPermission("action.view", adResourceKey, req)) {
//            throw new PermissionException();
//        }
        return employeeService.getDatatables(form);
    }
    
    @GetMapping
    public @ResponseBody List<EmployeeBO> getAll(HttpServletRequest req) {
      return employeeService.findAll();
  } 

    /**
     * saveOrUpdate EmployeeBO
     * 
     * @param req
     * @param form
     * @return
     * @throws Exception
     */
    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Response saveOrUpdate(HttpServletRequest req, @RequestBody EmployeeForm form)
            throws Exception {
        Long employeeId = CommonUtil.NVL(form.getEmployeeId());
        EmployeeBO employeeBO;
        if (employeeId > 0L) {
            employeeBO = employeeService.findById(employeeId);
            if (employeeBO == null) {
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
            employeeBO.setModifiedDate(new Date());
            employeeBO.setModifiedBy(CommonUtil.getUserLoginName(req));
            employeeBO.setStatus(form.getStatus());
        } else {
            employeeBO = new EmployeeBO();
            employeeBO.setCreatedDate(new Date());
            employeeBO.setCreatedBy(CommonUtil.getUserLoginName(req));
            employeeBO.setStatus(Constants.STATUS.EFFECTIVE);
        }
        employeeBO.setEmployeeCode(form.getEmployeeCode());
        employeeBO.setEmployeeName(form.getEmployeeName());
        employeeBO.setDateOfBirth(form.getDateOfBirth());
        employeeBO.setGender(form.getGender());
        employeeBO.setEmail(form.getEmail());
        employeeBO.setPhoneNumber(form.getPhoneNumber());
        employeeBO.setUserId(form.getUserId());
        employeeBO.setDepartmentId(form.getDepartmentId());
        employeeBO.setPositionId(form.getPositionId());
        employeeBO.setAddress(form.getAddress());
        employeeService.saveOrUpdate(employeeBO);
        if (employeeId <= 0L) {
            GenCodeEmployeeBO genCodeEmployeeBO = new GenCodeEmployeeBO();
            Long idCode = Long.valueOf(employeeBO.getEmployeeCode().split("-")[1]);
            genCodeEmployeeBO.setIdCode(idCode);
            genCodeEmployeeService.saveOrUpdate(genCodeEmployeeBO);
        }
        EmployeeImagesBO employeeImageBo;
        if (employeeId > 0L) {
            employeeImageBo = employeeImagesService.getEmployeeImageByEmployeeIdBO(employeeId);
            if (employeeImageBo != null) {
                employeeImageBo.setEmployeeImgUrl(form.getEmployeeImgUrl());
                employeeImageBo.setEmployeeId(employeeId);
                // delete old file and add new file
                String base64String = employeeImageBo.getEmployeeImgUrl();
                String[] strings = base64String.split(",");
                String extension;
                switch (strings[0]) {// check image's extension
                case "data:image/jpeg;base64":
                    extension = "jpeg";
                    break;
                case "data:image/png;base64":
                    extension = "png";
                    break;
                default:// should write cases for more images types
                    extension = "jpg";
                    break;
                }
                String path = "../assets/img/users/" + employeeBO.getEmployeeCode() + "." + extension;
                employeeImageBo.setImgName(employeeBO.getEmployeeCode() + "." + extension);
                try {
                    File f = new File(path);
                    if (f.delete()) {
                        System.out.println(f.getName() + " deleted");
                        employeeImagesService.saveImageToDirectory(form.getEmployeeImgUrl(),
                                employeeBO.getEmployeeCode(), employeeImageBo);
                    } else {
                        System.out.println("failed");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                employeeImagesService.saveOrUpdate(employeeImageBo);
            } else {
            	if (!CommonUtil.isNullOrEmpty(form.getEmployeeImgUrl())) {
                    employeeImageBo = new EmployeeImagesBO();
                    employeeImageBo.setEmployeeId(employeeBO.getEmployeeId());
                    employeeImageBo.setEmployeeImgUrl(form.getEmployeeImgUrl());
                    employeeImagesService.saveImageToDirectory(form.getEmployeeImgUrl(), employeeBO.getEmployeeCode(), employeeImageBo);
                    employeeImagesService.saveOrUpdate(employeeImageBo);
                }
            }
        } else {
            if (!CommonUtil.isNullOrEmpty(form.getEmployeeImgUrl())) {
                employeeImageBo = new EmployeeImagesBO();
                employeeImageBo.setEmployeeId(employeeBO.getEmployeeId());
                employeeImageBo.setEmployeeImgUrl(form.getEmployeeImgUrl());
                employeeImagesService.saveImageToDirectory(form.getEmployeeImgUrl(), employeeBO.getEmployeeCode(), employeeImageBo);
                employeeImagesService.saveOrUpdate(employeeImageBo);
            }
        }
        Long roleId = null;
        List<RoleBO> roleBO = roleService.findAll();
        for (RoleBO bo : roleBO) {
            if ("ROLE_NV".equals(bo.getRole())) {
                roleId = bo.getRoleId();
            }
        }
        // tạo user
        if (employeeId == 0L) {
            UserBO userBO = new UserBO();
            userBO.setUserName(employeeBO.getEmail());
            userBO.setPassword("123456a@A");
            userBO.setCreatedDate(new Date());
            userBO.setEmployeeId(employeeBO.getEmployeeId());
            userBO.setRoleId(roleId);
            userService.saveOrUpdate(userBO);
            // set to user_role
            UserRoleBO userRoleBO = new UserRoleBO();
            userRoleBO.setRoleId(roleId);
            userRoleBO.setUserId(userBO.getUserId());
            userRoleService.saveOrUpdate(userRoleBO);
            UserEmail userEmail = new UserEmail();
            userEmail.setLastName(employeeBO.getEmployeeName());
            userEmail.setEmailAddress(employeeBO.getEmail());
            String msg = String.format("Chào mừng %s đến với công ty!\n"
                    + "Tài khoản và mật khẩu hệ thống chấm công DHRM của bạn như sau: \n"
                    + "Tài khoản: %s \n"
                    + "Mật khẩu: %s \n"
                    + "Bạn hãy tiến hành đổi mật khẩu để bảo mật tài khoản.\n"
                    + "Xin trân trọng cảm ơn.", 
                    employeeBO.getEmployeeName(), userBO.getUserName(), userBO.getPassword());
            try {
                mailService.sendEmail(userEmail, msg, "Thông báo tài khoản hệ thống chấm công");
            } catch (MailException e) {
                System.out.println(e);
            }
        }
        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(employeeBO);
    }

    /**
     * delete
     * 
     * @param employeeId
     * @return
     */
    @DeleteMapping(path = "/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Response delete(HttpServletRequest req, @PathVariable Long employeeId) {
        EmployeeBO bo;
        EmployeeImagesBO employeeImageBO;
        List<TimekeepingBO> timekeepingBO;
        if (employeeId > 0L) {
            bo = employeeService.findById(employeeId);
            if (bo == null) {
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
            employeeImageBO = employeeImagesService.getEmployeeImageByEmployeeIdBO(employeeId);
            if (employeeImageBO == null) {
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
            timekeepingBO = timekeepingService.getListTimekeepingByEmployeeId(employeeId);
            String path1 = "../assets/img/users/" + employeeImageBO.getImgName();
            String path2 = "../face-recognition/uploads/" + employeeImageBO.getImgName();
            String path3 = "../face-recognition/embeddings/" + bo.getEmployeeCode() + ".npy";
            try {
                File f = new File(path1);
                if (f.delete()) {
                    System.out.println(f.getName() + " deleted");
                } else {
                    System.out.println("failed");
                }
                File f1 = new File(path2);
                if (f1.delete()) {
                    System.out.println(f1.getName() + " deleted");
                } else {
                    System.out.println("failed");
                }
                File f2 = new File(path3);
                if (f2.delete()) {
                    System.out.println(f2.getName() + " deleted");
                } else {
                    System.out.println("failed");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (TimekeepingBO item : timekeepingBO) {
                timekeepingService.delete(item);
            }
            employeeImagesService.delete(employeeImageBO);
            employeeService.delete(bo);
            return Response.success(Constants.RESPONSE_CODE.DELETE_SUCCESS);
        } else {
            return Response.error(Constants.RESPONSE_CODE.ERROR);
        }
    }
    
    @PostMapping(path = "change-avatar")
    public @ResponseBody Response changeAvatar(HttpServletRequest req, @RequestBody UserForm form)
            throws Exception {
        Long employeeId = CommonUtil.NVL(form.getEmployeeId());
        EmployeeImagesBO bo = employeeImagesService.getEmployeeImageByEmployeeIdBO(employeeId);
        if (bo != null) {
            bo.setEmployeeImgUrl(form.getEmployeeImgUrl());
            bo.setEmployeeId(employeeId);
            employeeImagesService.saveOrUpdate(bo);
            
            EmployeeBO employeeBO = employeeService.findById(employeeId);
            
            // delete old file and add new file
            String base64String = form.getEmployeeImgUrl();
            String[] strings = base64String.split(",");
            String extension;
            switch (strings[0]) {// check image's extension
            case "data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "data:image/png;base64":
                extension = "png";
                break;
            default:// should write cases for more images types
                extension = "jpg";
                break;
            }
            String path = "../assets/img/users/" + employeeBO.getEmployeeCode() + "." + extension;
            try {
                File f = new File(path);
                if (f.delete()) {
                    System.out.println(f.getName() + " deleted");
                    employeeImagesService.saveImageToDirectory(form.getEmployeeImgUrl(),
                            employeeBO.getEmployeeCode(), bo);
                } else {
                    System.out.println("failed");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(bo);
    }
}
