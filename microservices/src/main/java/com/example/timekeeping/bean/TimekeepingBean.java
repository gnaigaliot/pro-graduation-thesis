package com.example.timekeeping.bean;

import java.util.Date;

public class TimekeepingBean {
    private Long timekeepingId;
    private Long employeeId;
    private Integer isLate;
    private Integer leftEarly;
    private Date dateTimekeeping;
    private String departureTime;
    private String arrivalTime;
    private String departurePicture;
    private String arrivalPicture;
    private String arrivalLateTime;
    private String departureEarlyTime;
    private String employeeName;
    
    public Long getTimekeepingId() {
        return timekeepingId;
    }
    public void setTimekeepingId(Long timekeepingId) {
        this.timekeepingId = timekeepingId;
    }
    public Long getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    public Integer getIsLate() {
        return isLate;
    }
    public void setIsLate(Integer isLate) {
        this.isLate = isLate;
    }
    public Integer getLeftEarly() {
        return leftEarly;
    }
    public void setLeftEarly(Integer leftEarly) {
        this.leftEarly = leftEarly;
    }
    public Date getDateTimekeeping() {
        return dateTimekeeping;
    }
    public void setDateTimekeeping(Date dateTimekeeping) {
        this.dateTimekeeping = dateTimekeeping;
    }
    public String getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
    public String getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public String getDeparturePicture() {
        return departurePicture;
    }
    public void setDeparturePicture(String departurePicture) {
        this.departurePicture = departurePicture;
    }
    public String getArrivalPicture() {
        return arrivalPicture;
    }
    public void setArrivalPicture(String arrivalPicture) {
        this.arrivalPicture = arrivalPicture;
    }
    public String getArrivalLateTime() {
        return arrivalLateTime;
    }
    public void setArrivalLateTime(String arrivalLateTime) {
        this.arrivalLateTime = arrivalLateTime;
    }
    public String getDepartureEarlyTime() {
        return departureEarlyTime;
    }
    public void setDepartureEarlyTime(String departureEarlyTime) {
        this.departureEarlyTime = departureEarlyTime;
    }
    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
