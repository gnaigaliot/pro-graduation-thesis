package com.example.position.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "positions")
public class PositionBO implements Serializable{

    @Id
    @Column(name = "position_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long positionId;
    
    @Column(name = "position_code")
    private String positionCode;
    
    @Column(name = "position_name")
    private String positionName;
    
    @Column(name = "salary")
    private Double salary;
    
    @Column(name = "status")
    private int status;
    
    @Column(name = "created_date")
    private Date createdDate;
    
    @Column(name = "modified_date")
    private Date modifiedDate;
    
    @Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "modified_by")
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
