package com.example.position.entity;

import java.util.Date;

public class PositionBean {
    private Long positionId;
    private String positionCode;
    private String positionName;
    private Double salary;
    private int status;
    private Date createdDate;
    private Date modifiedDate;
    private String createdBy;
    private String modifiedBy;
    
    public Long getPositionId() {
        return positionId;
    }
    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }
    public String getPositionCode() {
        return positionCode;
    }
    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }
    public String getPositionName() {
        return positionName;
    }
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
    public Double getSalary() {
        return salary;
    }
    public void setSalary(Double salary) {
        this.salary = salary;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public Date getModifiedDate() {
        return modifiedDate;
    }
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public String getModifiedBy() {
        return modifiedBy;
    }
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
