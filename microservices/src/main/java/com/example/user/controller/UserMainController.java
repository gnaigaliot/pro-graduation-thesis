package com.example.user.controller;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.CommonUtil;
import com.example.common.Constants;
import com.example.common.DataTableResults;
import com.example.common.Response;
import com.example.employeeManager.department.bo.DepartmentBO;
import com.example.employeeManager.department.service.DepartmentService;
import com.example.employeeManager.employee.bo.EmployeeBO;
import com.example.employeeManager.employee.service.EmployeeService;
import com.example.employeeManager.employeeImages.bo.EmployeeImagesBO;
import com.example.employeeManager.employeeImages.service.EmployeeImagesService;
import com.example.employeeManager.position.bo.PositionsBO;
import com.example.employeeManager.position.service.PositionsService;
import com.example.exception.SysException;
import com.example.user.entity.RoleBO;
import com.example.user.entity.UserBO;
import com.example.user.entity.UserBean;
import com.example.user.entity.UserForm;
import com.example.user.entity.UserRoleBO;
import com.example.user.service.JwtService;
import com.example.user.service.UserRoleService;
import com.example.user.service.UserService;

@RestController
@RequestMapping("/rest")
public class UserMainController {
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRoleService userRoleService;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private EmployeeImagesService employeeImagesService;
    
    @Autowired
    private PositionsService positionsService;
    
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<UserBO> listUser(){
        return userService.findAll();
    }
    
    @GetMapping(path = "/search-user")
    public @ResponseBody DataTableResults<UserBean> getDatatables(HttpServletRequest req,UserForm user){
        return userService.getDatatable(user, req);
    }
    
    @GetMapping(path = "/get-roles")
    public List<RoleBO> getAllRole(){
        return userService.findAllRole();
    }
    
    @GetMapping(path = "/{id}")
    public @ResponseBody Response findById(HttpServletRequest req, @PathVariable Long id) throws Exception {
        UserBO bo = userService.findById(id);
        if (bo == null) {
            return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
        }
        List<Long> lstRoleId = userRoleService.findRoleByUserId(id);
        UserForm userForm = new UserForm();
        CommonUtil.copyProperties(userForm, bo);
        userForm.setLstRoleId(lstRoleId);
        if (bo.getEmployeeId() != null && bo.getEmployeeId() > 0L) {
            EmployeeImagesBO employeeImgBO = employeeImagesService.getEmployeeImageByEmployeeIdBO(bo.getEmployeeId());
            EmployeeBO empBO = employeeService.findById(bo.getEmployeeId());
            if (employeeImgBO != null) {
                userForm.setEmployeeImgUrl(employeeImgBO.getEmployeeImgUrl());
            }
            if (empBO != null) {
                userForm.setEmployeeCode(empBO.getEmployeeCode());
                PositionsBO posBO = positionsService.findById(empBO.getPositionId());
                DepartmentBO departBO = departmentService.findById(empBO.getDepartmentId());
                userForm.setPositionName(posBO.getPositionName());
                userForm.setDepartmentName(departBO.getDepartmentName());
                userForm.setEmployeeName(empBO.getEmployeeName());
                userForm.setEmail(empBO.getEmail());
                userForm.setPhoneNumber(empBO.getPhoneNumber());
                userForm.setDateOfBirth(empBO.getDateOfBirth());
                userForm.setGender(empBO.getGender().longValue());
                userForm.setEmployeeId(empBO.getEmployeeId());
            }
        }
        return Response.success().withData(userForm);
    }
    
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Response create(HttpServletRequest req, @RequestBody UserForm form) 
            throws Exception, SysException {
        Long userId = CommonUtil.NVL(form.getUserId());
        UserBO bo;
        if(userId > 0L) {
            bo = userService.findById(userId);
            if(bo == null) {
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
        } else {
            bo = new UserBO();
            form.setPassword("123456aA@");
            bo.setCreatedDate(new Date());
            bo.setCreatedBy(jwtService.getUsernameFromRequest(req));
        }
        bo.setUserName(form.getUserName());
        bo.setPassword(form.getPassword());
        bo.setRoleId(1L); // chỗ này phải sửa
        // phaan quyen
        /// xoa quyen cu
        userService.saveOrUpdate(bo);
        userRoleService.deleteAllByUserId(userId);
        /// Them quyen moi
        List<Long> lstRole = form.getLstRoleId();
        for (Long roleId : lstRole) {
            UserRoleBO userRoleBO = new UserRoleBO();
            userRoleBO.setUserId(bo.getUserId());
            userRoleBO.setRoleId(roleId);
            userRoleService.saveOrUpdate(userRoleBO);
        }
        if (bo.getEmployeeId() != null || bo.getEmployeeId() > 0L) {
            EmployeeBO empBO = employeeService.findById(bo.getEmployeeId());
            if(empBO != null) {
                empBO.setEmployeeName(form.getEmployeeName());
                empBO.setDateOfBirth(form.getDateOfBirth());
                empBO.setGender(form.getGender().intValue());
                empBO.setEmail(form.getEmail());
                empBO.setPhoneNumber(form.getPhoneNumber());
                employeeService.saveOrUpdate(empBO);
            }
        }
        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(bo);
    }

    
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Response delete(HttpServletRequest req,@PathVariable Long id) throws SysException, InstantiationException, IllegalAccessException {
        if(id > 0L) {
            UserBO bo = userService.findById(id);
            if (bo != null) {
//                if (!permissionChecker.hasPermission("action.delete", adResouceKey, req)) {
//                    return Response.invalidPermission();
//                 }
                userService.delete(bo);
                return Response.success(Constants.RESPONSE_CODE.DELETE_SUCCESS);
            } else {
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
        } else {
            return Response.error(Constants.RESPONSE_CODE.ERROR);
        }
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(HttpServletRequest request, @RequestBody UserForm user) {
        String result = "";
        HttpStatus httpStatus = null;
        try {
            if (userService.checkLogin(user)) {
                result = jwtService.generateTokenLogin(user.getUserName());
                httpStatus = HttpStatus.OK;
                UserBean userBean = userService.loadUserByUsername(user.getUserName());
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userBean, null,
                        AuthorityUtils.createAuthorityList(userBean.getRole()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                result = "Wrong userId and password";
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception ex) {
            result = "Server Error";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<String>(result, httpStatus);
    }

    @RequestMapping(value = "/currentUserInfo", method = RequestMethod.GET)
    public UserBean getCurrentUserInfo() {
        String userName = (String) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return userService.getUserByUsername(userName);
    }
    
    @RequestMapping(value = "/userInfo/{userId}", method = RequestMethod.GET)
    public UserBean getUserInfo(@PathVariable Long userId) {
        return userService.getUserInfoById(userId);
    }

    @PostMapping(path = "/update-info")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Response saveOrUpdateInfo(HttpServletRequest req, @RequestBody UserForm form) throws Exception {
        Long userId = CommonUtil.NVL(form.getUserId());
        UserBO bo = userService.findById(userId);
        if (bo != null) {
            bo.setUserName(form.getUserName());
            bo.setPassword(form.getPassword());
            userService.saveOrUpdate(bo);
            if (bo.getEmployeeId() != null || bo.getEmployeeId() > 0L) {
                EmployeeBO empBO = employeeService.findById(bo.getEmployeeId());
                if(empBO != null) {
                    empBO.setEmployeeName(form.getEmployeeName());
                    empBO.setDateOfBirth(form.getDateOfBirth());
                    empBO.setGender(form.getGender().intValue());
                    empBO.setEmail(form.getEmail());
                    empBO.setPhoneNumber(form.getPhoneNumber());
                    employeeService.saveOrUpdate(empBO);
                }
            }
        }
        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(bo);
    }
    
    @PostMapping(path = "/change-password")
    public @ResponseBody Response changePassword(HttpServletRequest req, @RequestBody UserForm form) throws Exception {
        Long userId = CommonUtil.NVL(form.getUserId());
        UserBO bo = userService.findById(userId);
        if (bo != null) {
            bo.setPassword(form.getNewPassword().split(" ").toString());
            userService.saveOrUpdate(bo);
        }
        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(bo);
    }
}
