package com.example.report;

import java.util.Date;

public class ReportForm {
    private Date firstDateOfWeek;
    private Date lastDateOfWeek;
    
    public Date getFirstDateOfWeek() {
        return firstDateOfWeek;
    }
    
    public void setFirstDateOfWeek(Date firstDateOfWeek) {
        this.firstDateOfWeek = firstDateOfWeek;
    }
    
    public Date getLastDateOfWeek() {
        return lastDateOfWeek;
    }
    
    public void setLastDateOfWeek(Date lastDateOfWeek) {
        this.lastDateOfWeek = lastDateOfWeek;
    }
    
}
