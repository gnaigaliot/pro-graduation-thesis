package com.example.report;

import java.util.List;

public class ReportBean {
    private List<Integer> listAttendance;
    private List<Integer> listChecinLate;
    private List<Integer> listDepartureEarly;
    private List<Integer> listDataPieChart;
    
    public List<Integer> getListAttendance() {
        return listAttendance;
    }
    
    public void setListAttendance(List<Integer> listAttendance) {
        this.listAttendance = listAttendance;
    }
    
    public List<Integer> getListChecinLate() {
        return listChecinLate;
    }
    
    public void setListChecinLate(List<Integer> listChecinLate) {
        this.listChecinLate = listChecinLate;
    }
    
    public List<Integer> getListDepartureEarly() {
        return listDepartureEarly;
    }
    
    public void setListDepartureEarly(List<Integer> listDepartureEarly) {
        this.listDepartureEarly = listDepartureEarly;
    }

    
    public List<Integer> getListDataPieChart() {
        return listDataPieChart;
    }

    
    public void setListDataPieChart(List<Integer> listDataPieChart) {
        this.listDataPieChart = listDataPieChart;
    }
}
