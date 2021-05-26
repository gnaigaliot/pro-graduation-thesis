package com.example.report;

import java.util.Date;

public class LineChartBean {
    private Date dateInWeek;
    private Integer arrivalLate;
    private Integer departureEarly;
    private Integer totalEmp;
    
    public Date getDateInWeek() {
        return dateInWeek;
    }
    
    public void setDateInWeek(Date dateInWeek) {
        this.dateInWeek = dateInWeek;
    }
    
    public Integer getArrivalLate() {
        return arrivalLate;
    }
    
    public void setArrivalLate(Integer arrivalLate) {
        this.arrivalLate = arrivalLate;
    }
    
    public Integer getDepartureEarly() {
        return departureEarly;
    }
    
    public void setDepartureEarly(Integer departureEarly) {
        this.departureEarly = departureEarly;
    }
    
    public Integer getTotalEmp() {
        return totalEmp;
    }
    
    public void setTotalEmp(Integer totalEmp) {
        this.totalEmp = totalEmp;
    }
    
}
