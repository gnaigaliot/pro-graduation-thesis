package com.example.employeeManager.employee.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.example.employeeManager.employee.bean.EmployeeBean;
import com.example.employeeManager.employee.bo.EmployeeBO;
import com.example.employeeManager.employee.form.EmployeeForm;
import com.example.employeeManager.employee.service.EmployeeService;

@Controller
@RequestMapping("/v1/employee-manager/employee")
public class EmployeeController extends BaseController {

    private static String adResourceKey = "resource.employee";

    @Autowired
    private EmployeeService employeeService;

    /**
     * findById
     * @param employeeId
     * @return
     */
    @GetMapping(path = "/{employeeId}")
    public @ResponseBody Response findById(HttpServletRequest req, @PathVariable Long employeeId) {
//        if (! permissionChecker.hasPermission("action.view", adResourceKey, req)) {
//            return Response.invalidPermission();
//        }
        EmployeeBO employeeBO = employeeService.findById(employeeId);
        if(employeeBO == null) {
            return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
        }
        return Response.success().withData(employeeBO);
    }

    /**
     * processSearch
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

    /**
     * saveOrUpdate EmployeeBO
     * @param req
     * @param form
     * @return
     * @throws Exception
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Response saveOrUpdate(HttpServletRequest req, @RequestBody EmployeeForm form) throws Exception {
        Long employeeId = CommonUtil.NVL(form.getEmployeeId());
        EmployeeBO employeeBO;
        if(employeeId > 0L) {
//            if (!permissionChecker.hasPermission("action.update", adResourceKey, req)) {
//                return Response.invalidPermission();
//            }
            employeeBO = employeeService.findById(employeeId);
            if(employeeBO == null){
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
        } else {
//            if (!permissionChecker.hasPermission("action.insert", adResourceKey, req)) {
//                return Response.invalidPermission();
//            }
            employeeBO = new EmployeeBO();
        }
        employeeBO.setEmployeeCode(form.getEmployeeCode());
        employeeBO.setEmployeeName(form.getEmployeeName());
        employeeBO.setDateOfBirth(form.getDateOfBirth());
        employeeBO.setGender(form.getGender());
        employeeBO.setEmail(form.getEmail());
        employeeBO.setPhoneNumber(form.getPhoneNumber());
        employeeBO.setStatus(form.getStatus());
        employeeBO.setUserId(form.getUserId());
        employeeService.saveOrUpdate(employeeBO);
        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(employeeBO);
    }

    /**
     * delete
     * @param employeeId
     * @return
     */
    @DeleteMapping(path = "/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Response delete(HttpServletRequest req, @PathVariable Long employeeId) {
//        if (! permissionChecker.hasPermission("action.delete", adResourceKey, req)) {
//            return Response.invalidPermission();
//        }
        EmployeeBO bo ;
        if(employeeId > 0L) {
            bo = employeeService.findById(employeeId);
            if(bo == null) {
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
            employeeService.delete(bo);
            return Response.success(Constants.RESPONSE_CODE.DELETE_SUCCESS);
        } else {
            return Response.error(Constants.RESPONSE_CODE.ERROR);
        }
    }
}
