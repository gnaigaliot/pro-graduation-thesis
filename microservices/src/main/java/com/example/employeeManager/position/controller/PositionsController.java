/*
 * Copyright (C) 2018 Viettel Telecom. All rights reserved. VIETTEL PROPRIETARY/CONFIDENTIAL. Use is
 * subject to license terms.
 */
package com.example.employeeManager.position.controller;

import java.util.List;

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
import com.example.employeeManager.position.bean.PositionsBean;
import com.example.employeeManager.position.bo.PositionsBO;
import com.example.employeeManager.position.form.PositionsForm;
import com.example.employeeManager.position.service.PositionsService;

@Controller
@RequestMapping("/v1/employee-manager/position")
public class PositionsController extends BaseController {

    private static String adResourceKey = "resource.positions";

    @Autowired
    private PositionsService positionsService;

    /**
     * findById
     * @param positionsId
     * @return
     */
    @GetMapping(path = "/{positionsId}")
    public @ResponseBody Response findById(HttpServletRequest req, @PathVariable Long positionsId) {
//        if (! permissionChecker.hasPermission("action.view", adResourceKey, req)) {
//            return Response.invalidPermission();
//        }
        PositionsBO positionsBO = positionsService.findById(positionsId);
        if(positionsBO == null) {
            return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
        }
        return Response.success().withData(positionsBO);
    }

    /**
     * processSearch
     * @param PositionsForm form
     * @return DataTableResults
     */
    @GetMapping(path = "/search")
    public @ResponseBody DataTableResults<PositionsBean> processSearch(HttpServletRequest req, PositionsForm form) {
//        if (! permissionChecker.hasPermission("action.view", adResourceKey, req)) {
//            throw new PermissionException();
//        }
        return positionsService.getDatatables(form);
    }

    /**
     * saveOrUpdate PositionsBO
     * @param req
     * @param form
     * @return
     * @throws Exception
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Response saveOrUpdate(HttpServletRequest req, @RequestBody PositionsForm form) throws Exception {
        Long positionId = CommonUtil.NVL(form.getPositionId());
        PositionsBO positionsBO;
        if(positionId > 0L) {
//            if (!permissionChecker.hasPermission("action.update", adResourceKey, req)) {
//                return Response.invalidPermission();
//            }
            positionsBO = positionsService.findById(positionId);
            if(positionsBO == null){
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
        } else {
//            if (!permissionChecker.hasPermission("action.insert", adResourceKey, req)) {
//                return Response.invalidPermission();
//            }
            positionsBO = new PositionsBO();
        }
        positionsBO.setPositionCode(form.getPositionCode());
        positionsBO.setPositionName(form.getPositionName());
        positionsBO.setSalary(form.getSalary());
        positionsBO.setStatus(form.getStatus());
        positionsService.saveOrUpdate(positionsBO);
        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(positionsBO);
    }

    /**
     * delete
     * @param positionsId
     * @return
     */
    @DeleteMapping(path = "/{positionsId}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Response delete(HttpServletRequest req, @PathVariable Long positionsId) {
//        if (! permissionChecker.hasPermission("action.delete", adResourceKey, req)) {
//            return Response.invalidPermission();
//        }
        PositionsBO bo ;
        if(positionsId > 0L) {
            bo = positionsService.findById(positionsId);
            if(bo == null) {
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
            positionsService.delete(bo);
            return Response.success(Constants.RESPONSE_CODE.DELETE_SUCCESS);
        } else {
            return Response.error(Constants.RESPONSE_CODE.ERROR);
        }
    }
    
    @GetMapping(path = "/get-all")
    public @ResponseBody List<PositionsBO> getAll(HttpServletRequest req){
        return positionsService.getAll();
    }
}
