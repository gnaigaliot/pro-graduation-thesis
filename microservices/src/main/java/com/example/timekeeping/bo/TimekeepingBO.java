package com.example.timekeeping.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

@Entity
@Table(name = "timekeeping")
public class TimekeepingBO implements Serializable {
    @Id
    @Column(name = "timekeeping_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long timekeepingId;
    
    @Column(name = "employee_id")
    private Long employeeId;
    
    @Column(name = "date_timekeeping")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateTimekeeping;
    
    @Column(name = "arrival_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Date arrivalTime;
    
    @Column(name = "departure_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Date departureTime;
    
    @Column(name = "is_late")
    private Integer isLate;
    
    @Column(name = "left_early")
    private Integer leftEarly;
    
    @Column(name = "arrival_picture")
    private String arrivalPicture;
    
    @Column(name = "departure_picture")
    private String departurePicture;
    
    @Column(name = "arrival_late_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Date arrivalLateTime;
    
    @Column(name = "departure_early_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Date departureEarlyTime;

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

    public Date getDateTimekeeping() {
        return dateTimekeeping;
    }

    public void setDateTimekeeping(Date dateTimekeeping) {
        this.dateTimekeeping = dateTimekeeping;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
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

    public String getArrivalPicture() {
        return arrivalPicture;
    }

    public void setArrivalPicture(String arrivalPicture) {
        this.arrivalPicture = arrivalPicture;
    }

    public String getDeparturePicture() {
        return departurePicture;
    }

    public void setDeparturePicture(String departurePicture) {
        this.departurePicture = departurePicture;
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
}
