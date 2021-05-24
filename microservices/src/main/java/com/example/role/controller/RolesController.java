package com.example.role.controller;

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
import com.example.role.bean.RolesBean;
import com.example.role.bo.RolesBO;
import com.example.role.form.RolesForm;
import com.example.role.service.RolesService;

@Controller
@RequestMapping("/v1/role")
public class RolesController extends BaseController {

    private static String adResourceKey = "resource.roles";

    @Autowired
    private RolesService rolesService;

//    @Autowired
//    private PermissionChecker permissionChecker;

    /**
     * findById
     * @param rolesId
     * @return
     */
    @GetMapping(path = "/{rolesId}")
    public @ResponseBody Response findById(HttpServletRequest req, @PathVariable Long rolesId) {
        RolesBO rolesBO = rolesService.findById(rolesId);
        if(rolesBO == null) {
            return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
        }
        return Response.success().withData(rolesBO);
    }

    /**
     * processSearch
     * @param RolesForm form
     * @return DataTableResults
     */
    @GetMapping(path = "/search")
    public @ResponseBody DataTableResults<RolesBean> processSearch(HttpServletRequest req, RolesForm form) {
        return rolesService.getDatatables(form);
    }

    /**
     * saveOrUpdate RolesBO
     * @param req
     * @param form
     * @return
     * @throws Exception
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Response saveOrUpdate(HttpServletRequest req, @RequestBody RolesForm form) throws Exception {
        Long roleId = CommonUtil.NVL(form.getRoleId());
        RolesBO rolesBO;
        if(roleId > 0L) {
            rolesBO = rolesService.findById(roleId);
            if(rolesBO == null){
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
        } else {
            rolesBO = new RolesBO();
        }
        rolesBO.setRole(form.getRole());
        rolesBO.setRoleName(form.getRoleName());
        rolesBO.setDescription(form.getDescription());
        rolesService.saveOrUpdate(rolesBO);
        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(rolesBO);
    }

    /**
     * delete
     * @param rolesId
     * @return
     */
    @DeleteMapping(path = "/{rolesId}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Response delete(HttpServletRequest req, @PathVariable Long rolesId) {
        RolesBO bo ;
        if(rolesId > 0L) {
            bo = rolesService.findById(rolesId);
            if(bo == null) {
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
            rolesService.delete(bo);
            return Response.success(Constants.RESPONSE_CODE.DELETE_SUCCESS);
        } else {
            return Response.error(Constants.RESPONSE_CODE.ERROR);
        }
    }
}
