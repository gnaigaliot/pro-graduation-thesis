package com.example.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.CommonUtil;
import com.example.common.Response;
import com.example.employeeManager.employee.bo.EmployeeBO;
import com.example.employeeManager.employee.service.EmployeeService;
import com.example.timekeeping.service.TimekeepingService;

@RestController
@RequestMapping("/v1/report")
public class ReportController {
    @Autowired
    private TimekeepingService timekeepingService;
    
    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping(path = "/get-chart/{firstDate}/{lastDate}")
    public @ResponseBody Response getChart(HttpServletRequest req, @PathVariable Date firstDate, @PathVariable Date lastDate) {
        ReportBean bean = new ReportBean();
        List<LineChartBean> listLineChartData = timekeepingService.getLineChartData(firstDate, lastDate);
        List<Integer> listTotalEmp = new ArrayList<Integer>();
        List<Integer> listArrivalLate = new ArrayList<Integer>();
        List<Integer> listLeftEarly = new ArrayList<Integer>();
        for (LineChartBean item : listLineChartData) {
            listTotalEmp.add(item.getTotalEmp());
            listArrivalLate.add(item.getArrivalLate());
            listLeftEarly.add(item.getDepartureEarly());
        }
        bean.setListAttendance(listTotalEmp);
        bean.setListChecinLate(listArrivalLate);
        bean.setListDepartureEarly(listLeftEarly);
        return Response.success().withData(bean);
    }
    
    @GetMapping(path = "/get-pie-chart")
    public @ResponseBody Response getPieChart(HttpServletRequest req) {
        ReportBean bean = new ReportBean();
        List<PieChartBean> listPieData = employeeService.getPieChartData();
        List<Integer> listData = new ArrayList<Integer>();
        for (PieChartBean item : listPieData) {
            listData.add(item.getTotalEmployee());
        }
        bean.setListDataPieChart(listData);
        return Response.success().withData(bean);
    }
    
    @GetMapping(path= "/get-event")
    public @ResponseBody Response getEvent(HttpServletRequest req) {
        List<EventBean> lstEvent = new ArrayList<EventBean>();
        List<EmployeeBO> lstEmployee = employeeService.findAll();
        int i = 0;
        for (EmployeeBO emp : lstEmployee) {
            EventBean bean = new EventBean();
            if (emp.getDateOfBirth() != null && emp.getStatus() == 1) {
                bean.setStart(CommonUtil.convertDateToString(emp.getDateOfBirth(), "yyyy-MM-dd"));
                bean.setTitle(String.format("Sinh nhật của: %s", emp.getEmployeeName()));
                bean.setId(i++);
                lstEvent.add(bean);
            }
        }
        return Response.success().withData(lstEvent);
    }
}
