package com.example.report;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.Response;
import com.example.user.service.RoleService;

@RestController
@RequestMapping("/v1/report")
public class ReportController {
    
    @Autowired
    private RoleService roleService;
    
    @GetMapping(path = "get-chart")
    public @ResponseBody Response getChart(HttpServletRequest req, ReportForm form) {
        ReportBean bean = new ReportBean();
        
        
        return Response.success().withData(bean);
    }
}
