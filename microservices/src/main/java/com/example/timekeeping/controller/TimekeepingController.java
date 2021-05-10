package com.example.timekeeping.controller;

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
import com.example.timekeeping.bean.TimekeepingBean;
import com.example.timekeeping.bo.TimekeepingBO;
import com.example.timekeeping.form.TimekeepingForm;
import com.example.timekeeping.service.TimekeepingService;

@Controller
@RequestMapping("/v1/timekeeping")
public class TimekeepingController extends BaseController  {

    private static String adResourceKey = "resource.employee";

    @Autowired
    private TimekeepingService timekeepingService;

    /**
     * findById
     * @param timekeepingId
     * @return
     */
    @GetMapping(path = "/{timekeepingId}")
    public @ResponseBody Response findById(HttpServletRequest req, @PathVariable Long timekeepingId) {
//        if (! permissionChecker.hasPermission("action.view", adResourceKey, req)) {
//            return Response.invalidPermission();
//        }
        TimekeepingBO timekeepingBO = timekeepingService.findById(timekeepingId);
        if(timekeepingBO == null) {
            return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
        }
        return Response.success().withData(timekeepingBO);
    }

    /**
     * processSearch
     * @param TimekeepingForm form
     * @return DataTableResults
     */
    @GetMapping(path = "/search")
    public @ResponseBody DataTableResults<TimekeepingBean> processSearch(HttpServletRequest req, TimekeepingForm form) {
//        if (! permissionChecker.hasPermission("action.view", adResourceKey, req)) {
//            throw new PermissionException();
//        }
        return timekeepingService.getDatatables(form);
    }

    /**
     * saveOrUpdate TimekeepingBO
     * @param req
     * @param form
     * @return
     * @throws Exception
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Response saveOrUpdate(HttpServletRequest req, @RequestBody TimekeepingForm form) throws Exception {
        Long timekeepingId = CommonUtil.NVL(form.getTimekeepingId());
        TimekeepingBO timekeepingBO;
        if(timekeepingId > 0L) {
//            if (!permissionChecker.hasPermission("action.update", adResourceKey, req)) {
//                return Response.invalidPermission();
//            }
        	timekeepingBO = timekeepingService.findById(timekeepingId);
            if(timekeepingBO == null){
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
        } else {
//            if (!permissionChecker.hasPermission("action.insert", adResourceKey, req)) {
//                return Response.invalidPermission();
//            }
            timekeepingBO = new TimekeepingBO();
        }
        timekeepingBO.setEmployeeId(form.getEmployeeId());
        timekeepingBO.setIsLate(form.getIsLate());
        timekeepingBO.setLeftEarly(form.getLeftEarly());
        timekeepingBO.setDateTimekeeping(form.getDateTimekeeping());
        timekeepingBO.setDepartureTime(form.getDepartureTime());
        timekeepingBO.setDeparturePicture(form.getDeparturePicture());
        timekeepingBO.setArrivalTime(form.getArrivalTime());
        timekeepingBO.setArrivalPicture(form.getArrivalPicture());
        timekeepingBO.setArrivalLateTime(form.getArrivalLateTime());
        timekeepingBO.setDepartureEarlyTime(form.getDepartureEarlyTime());
        timekeepingService.saveOrUpdate(timekeepingBO);
        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(timekeepingBO);
    }

    /**
     * delete
     * @param timekeepingId
     * @return
     */
    @DeleteMapping(path = "/{timekeepingId}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Response delete(HttpServletRequest req, @PathVariable Long timekeepingId) {
//        if (! permissionChecker.hasPermission("action.delete", adResourceKey, req)) {
//            return Response.invalidPermission();
//        }
        TimekeepingBO bo ;
        if(timekeepingId > 0L) {
            bo = timekeepingService.findById(timekeepingId);
            if(bo == null) {
                return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
            }
            timekeepingService.delete(bo);
            return Response.success(Constants.RESPONSE_CODE.DELETE_SUCCESS);
        } else {
            return Response.error(Constants.RESPONSE_CODE.ERROR);
        }
    }
}
