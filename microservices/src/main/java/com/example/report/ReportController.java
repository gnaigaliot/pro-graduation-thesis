package com.example.report;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.Constants;
import com.example.common.Response;
import com.example.role.bo.RolesBO;

@RestController
@RequestMapping("/v1/report")
public class ReportController {
    
    @GetMapping(path = "get-information-card")
    public @ResponseBody Response getInformationCard(HttpServletRequest req) {
        RolesBO rolesBO = rolesService.findById(rolesId);
        if(rolesBO == null) {
            return Response.warning(Constants.RESPONSE_CODE.RECORD_DELETED);
        }
        return Response.success().withData(rolesBO);
    }
}
