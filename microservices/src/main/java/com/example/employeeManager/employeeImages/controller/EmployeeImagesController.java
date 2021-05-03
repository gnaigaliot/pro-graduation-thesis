package com.example.employeeManager.employeeImages.controller;

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
import com.example.employeeManager.employeeImages.bean.EmployeeImagesBean;
import com.example.employeeManager.employeeImages.bo.EmployeeImagesBO;
import com.example.employeeManager.employeeImages.form.EmployeeImagesForm;
import com.example.employeeManager.employeeImages.service.EmployeeImagesService;

/**
 * @author d2tsoftware
 * @since 04/05/2021
 * @version 1.0
 */
@Controller
@RequestMapping("/v1/employee-manager/employee-images")
public class EmployeeImagesController extends BaseController {

    private static String adResourceKey = "resource.employeeImages";

    @Autowired
    private EmployeeImagesService employeeImagesService;

//    @Autowired
//    private PermissionChecker permissionChecker;

    /**
     * findById
     * @param employeeImagesId
     * @return
     */
    @GetMapping(path = "/{employeeImagesId}")
    public @ResponseBody Response findById(HttpServletRequest req, @PathVariable Long employeeImagesId) {
//        if (! permissionChecker.hasPermission("action.view", adResourceKey, req)) {
//            return Response.invalidPermission();
//        }
        EmployeeImagesBO employeeImagesBO = employeeImagesService.findById(employeeImagesId);
        if(employeeImagesBO == null) {
            return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
        }
        return Response.success().withData(employeeImagesBO);
    }

    /**
     * processSearch
     * @param EmployeeImagesForm form
     * @return DataTableResults
     */
    @GetMapping(path = "/search")
    public @ResponseBody DataTableResults<EmployeeImagesBean> processSearch(HttpServletRequest req, EmployeeImagesForm form) {
//        if (! permissionChecker.hasPermission("action.view", adResourceKey, req)) {
//            throw new PermissionException();
//        }
        return employeeImagesService.getDatatables(form);
    }

    /**
     * saveOrUpdate EmployeeImagesBO
     * @param req
     * @param form
     * @return
     * @throws Exception
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Response saveOrUpdate(HttpServletRequest req, @RequestBody EmployeeImagesForm form) throws Exception {
        Long employeeImageId = CommonUtil.NVL(form.getEmployeeImageId());
        EmployeeImagesBO employeeImagesBO;
        if(employeeImageId > 0L) {
//            if (!permissionChecker.hasPermission("action.update", adResourceKey, req)) {
//                return Response.invalidPermission();
//            }
            employeeImagesBO = employeeImagesService.findById(employeeImageId);
            if(employeeImagesBO == null){
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
        } else {
//            if (!permissionChecker.hasPermission("action.insert", adResourceKey, req)) {
//                return Response.invalidPermission();
//            }
            employeeImagesBO = new EmployeeImagesBO();
        }
        employeeImagesBO.setEmployeeId(form.getEmployeeId());
        employeeImagesBO.setEmployeeImgUrl(form.getEmployeeImgUrl());
        employeeImagesService.saveOrUpdate(employeeImagesBO);
        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(employeeImagesBO);
    }

    /**
     * delete
     * @param employeeImagesId
     * @return
     */
    @DeleteMapping(path = "/{employeeImagesId}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Response delete(HttpServletRequest req, @PathVariable Long employeeImagesId) {
//        if (! permissionChecker.hasPermission("action.delete", adResourceKey, req)) {
//            return Response.invalidPermission();
//        }
        EmployeeImagesBO bo ;
        if(employeeImagesId > 0L) {
            bo = employeeImagesService.findById(employeeImagesId);
            if(bo == null) {
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
            employeeImagesService.delete(bo);
            return Response.success(Constants.RESPONSE_CODE.DELETE_SUCCESS);
        } else {
            return Response.error(Constants.RESPONSE_CODE.ERROR);
        }
    }
}
