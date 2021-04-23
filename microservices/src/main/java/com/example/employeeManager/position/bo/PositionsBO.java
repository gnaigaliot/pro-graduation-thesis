/*
 * Copyright (C) 2018 Viettel Telecom. All rights reserved. VIETTEL PROPRIETARY/CONFIDENTIAL. Use is
 * subject to license terms.
 */

package com.example.employeeManager.position.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "positions")
public class PositionsBO {

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


    /**
     * Set the "positionId" field value
     * @param positionId
     */
    public void setPositionId( Long positionId ) {
        this.positionId = positionId ;
    }

    /**
     * Get the "positionId" field value
     * @return the field value
     */
    public Long getPositionId() {
        return this.positionId;
    }
    /**
     * Set the "positionCode" field value
     * @param positionCode
     */
    public void setPositionCode( String positionCode ) {
        this.positionCode = positionCode ;
    }

    /**
     * Get the "positionCode" field value
     * @return the field value
     */
    public String getPositionCode() {
        return this.positionCode;
    }
    /**
     * Set the "positionName" field value
     * @param positionName
     */
    public void setPositionName( String positionName ) {
        this.positionName = positionName ;
    }

    /**
     * Get the "positionName" field value
     * @return the field value
     */
    public String getPositionName() {
        return this.positionName;
    }
    /**
     * Set the "salary" field value
     * @param salary
     */
    public void setSalary( Double salary ) {
        this.salary = salary ;
    }

    /**
     * Get the "salary" field value
     * @return the field value
     */
    public Double getSalary() {
        return this.salary;
    }
    /**
     * Set the "status" field value
     * @param status
     */
    public void setStatus( int status ) {
        this.status = status ;
    }

    /**
     * Get the "status" field value
     * @return the field value
     */
    public int getStatus() {
        return this.status;
    }
    /**
     * Set the "createdDate" field value
     * @param createdDate
     */
    public void setCreatedDate( Date createdDate ) {
        this.createdDate = createdDate ;
    }

    /**
     * Get the "createdDate" field value
     * @return the field value
     */
    public Date getCreatedDate() {
        return this.createdDate;
    }
    /**
     * Set the "modifiedDate" field value
     * @param modifiedDate
     */
    public void setModifiedDate( Date modifiedDate ) {
        this.modifiedDate = modifiedDate ;
    }

    /**
     * Get the "modifiedDate" field value
     * @return the field value
     */
    public Date getModifiedDate() {
        return this.modifiedDate;
    }
    /**
     * Set the "createdBy" field value
     * @param createdBy
     */
    public void setCreatedBy( String createdBy ) {
        this.createdBy = createdBy ;
    }

    /**
     * Get the "createdBy" field value
     * @return the field value
     */
    public String getCreatedBy() {
        return this.createdBy;
    }
    /**
     * Set the "modifiedBy" field value
     * @param modifiedBy
     */
    public void setModifiedBy( String modifiedBy ) {
        this.modifiedBy = modifiedBy ;
    }

    /**
     * Get the "modifiedBy" field value
     * @return the field value
     */
    public String getModifiedBy() {
        return this.modifiedBy;
    }

}
