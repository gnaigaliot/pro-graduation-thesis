/*
 * Copyright (C) 2018 Viettel Telecom. All rights reserved. VIETTEL PROPRIETARY/CONFIDENTIAL. Use is
 * subject to license terms.
 */
package com.example.employeeManager.department.controller;

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
import com.example.employeeManager.department.bean.DepartmentBean;
import com.example.employeeManager.department.bo.DepartmentBO;
import com.example.employeeManager.department.form.DepartmentForm;
import com.example.employeeManager.department.service.DepartmentService;

/**
 * @author d2tsoftware
 * @since 24/04/2021
 * @version 1.0
 */
@Controller
@RequestMapping("/v1/employee-manager/department")
public class DepartmentController extends BaseController {

    private static String adResourceKey = "resource.department";

    @Autowired
    private DepartmentService departmentService;

    /**
     * findById
     * @param departmentId
     * @return
     */
    @GetMapping(path = "/{departmentId}")
    public @ResponseBody Response findById(HttpServletRequest req, @PathVariable Long departmentId) {
//        if (! permissionChecker.hasPermission("action.view", adResourceKey, req)) {
//            return Response.invalidPermission();
//        }
        DepartmentBO departmentBO = departmentService.findById(departmentId);
        if(departmentBO == null) {
            return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
        }
        return Response.success().withData(departmentBO);
    }

    /**
     * processSearch
     * @param DepartmentForm form
     * @return DataTableResults
     */
    @GetMapping(path = "/search")
    public @ResponseBody DataTableResults<DepartmentBean> processSearch(HttpServletRequest req, DepartmentForm form) {
//        if (! permissionChecker.hasPermission("action.view", adResourceKey, req)) {
//            throw new PermissionException();
//        }
        return departmentService.getDatatables(form);
    }

    /**
     * saveOrUpdate DepartmentBO
     * @param req
     * @param form
     * @return
     * @throws Exception
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Response saveOrUpdate(HttpServletRequest req, @RequestBody DepartmentForm form) throws Exception {
        Long departmentId = CommonUtil.NVL(form.getDepartmentId());
        DepartmentBO departmentBO;
        if(departmentId > 0L) {
//            if (!permissionChecker.hasPermission("action.update", adResourceKey, req)) {
//                return Response.invalidPermission();
//            }
            departmentBO = departmentService.findById(departmentId);
            if(departmentBO == null){
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
            departmentBO.setStatusDepartment(form.getStatusDepartment());
        } else {
//            if (!permissionChecker.hasPermission("action.insert", adResourceKey, req)) {
//                return Response.invalidPermission();
//            }
            departmentBO = new DepartmentBO();
            departmentBO.setStatusDepartment(Constants.STATUS_DEPARTMENT.EFFECTIVE);
        }
        departmentBO.setDepartmentCode(form.getDepartmentCode());
        departmentBO.setDepartmentName(form.getDepartmentName());
        departmentService.saveOrUpdate(departmentBO);
        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(departmentBO);
    }

    /**
     * delete
     * @param departmentId
     * @return
     */
    @DeleteMapping(path = "/{departmentId}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Response delete(HttpServletRequest req, @PathVariable Long departmentId) {
//        if (! permissionChecker.hasPermission("action.delete", adResourceKey, req)) {
//            return Response.invalidPermission();
//        }
        DepartmentBO bo ;
        if(departmentId > 0L) {
            bo = departmentService.findById(departmentId);
            if(bo == null) {
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
            departmentService.delete(bo);
            return Response.success(Constants.RESPONSE_CODE.DELETE_SUCCESS);
        } else {
            return Response.error(Constants.RESPONSE_CODE.ERROR);
        }
    }
}
