package com.example.genCodeEmployee.controller;

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
import com.example.genCodeEmployee.bean.GenCodeEmployeeBean;
import com.example.genCodeEmployee.bo.GenCodeEmployeeBO;
import com.example.genCodeEmployee.form.GenCodeEmployeeForm;
import com.example.genCodeEmployee.service.GenCodeEmployeeService;

@Controller
@RequestMapping("/v1/gencode-employee")
public class GenCodeEmployeeController extends BaseController {

    @Autowired
    private GenCodeEmployeeService genCodeEmployeeService;

    /**
     * findById
     * @param genCodeEmployeeId
     * @return
     */
    @GetMapping(path = "/{genCodeEmployeeId}")
    public @ResponseBody Response findById(HttpServletRequest req, @PathVariable Long genCodeEmployeeId) {
        GenCodeEmployeeBO genCodeEmployeeBO = genCodeEmployeeService.findById(genCodeEmployeeId);
        if(genCodeEmployeeBO == null) {
            return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
        }
        return Response.success().withData(genCodeEmployeeBO);
    }

    /**
     * processSearch
     * @param GenCodeEmployeeForm form
     * @return DataTableResults
     */
    @GetMapping(path = "/search")
    public @ResponseBody DataTableResults<GenCodeEmployeeBean> processSearch(HttpServletRequest req, GenCodeEmployeeForm form) {
        return genCodeEmployeeService.getDatatables(form);
    }

    /**
     * saveOrUpdate GenCodeEmployeeBO
     * @param req
     * @param form
     * @return
     * @throws Exception
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Response saveOrUpdate(HttpServletRequest req, @RequestBody GenCodeEmployeeForm form) throws Exception {
        Long genCodeId = CommonUtil.NVL(form.getGenCodeId());
        GenCodeEmployeeBO genCodeEmployeeBO;
        if(genCodeId > 0L) {
            genCodeEmployeeBO = genCodeEmployeeService.findById(genCodeId);
            if(genCodeEmployeeBO == null){
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
        } else {
            genCodeEmployeeBO = new GenCodeEmployeeBO();
        }
        genCodeEmployeeBO.setIdCode(form.getIdCode());
        genCodeEmployeeService.saveOrUpdate(genCodeEmployeeBO);
        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(genCodeEmployeeBO);
    }

    /**
     * delete
     * @param genCodeEmployeeId
     * @return
     */
    @DeleteMapping(path = "/{genCodeEmployeeId}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Response delete(HttpServletRequest req, @PathVariable Long genCodeEmployeeId) {
        GenCodeEmployeeBO bo ;
        if(genCodeEmployeeId > 0L) {
            bo = genCodeEmployeeService.findById(genCodeEmployeeId);
            if(bo == null) {
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
            genCodeEmployeeService.delete(bo);
            return Response.success(Constants.RESPONSE_CODE.DELETE_SUCCESS);
        } else {
            return Response.error(Constants.RESPONSE_CODE.ERROR);
        }
    }
    
    @GetMapping(path = "/last-code")
    public @ResponseBody Long getLastCodeNumberEmployee(HttpServletRequest req) {
        return genCodeEmployeeService.getLastCodeNumberEmployee();
    }
}
