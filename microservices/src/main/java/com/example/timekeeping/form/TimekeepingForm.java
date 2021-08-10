package com.example.timekeeping.form;

import java.util.Date;
import java.util.List;

public class TimekeepingForm {

    private Long timekeepingId;
    private Long employeeId;
    private Integer isLate;
    private Integer leftEarly;
    private Date dateTimekeeping;
    private Date departureTime;
    private Date arrivalTime;
    private String departurePicture;
    private String arrivalPicture;
    private Date arrivalLateTime;
    private Date departureEarlyTime;
    private String employeeName;
    private Boolean isAdmin;
    private Long departmentId;

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

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
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

    public Date getArrivalLateTime() {
        return arrivalLateTime;
    }

    public void setArrivalLateTime(Date arrivalLateTime) {
        this.arrivalLateTime = arrivalLateTime;
    }

    public Date getDepartureEarlyTime() {
        return departureEarlyTime;
    }

    public void setDepartureEarlyTime(Date departureEarlyTime) {
        this.departureEarlyTime = departureEarlyTime;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
