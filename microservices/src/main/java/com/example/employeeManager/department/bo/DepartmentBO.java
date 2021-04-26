/*
 * Copyright (C) 2018 Viettel Telecom. All rights reserved. VIETTEL PROPRIETARY/CONFIDENTIAL. Use is
 * subject to license terms.
 */

package com.example.employeeManager.department.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class DepartmentBO {

    @Id
    @Column(name = "department_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    @Column(name = "department_code")
    private String departmentCode;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "status_department")
    private Integer statusDepartment;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "modified_date")
    private Date modifiedDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;


    /**
     * Set the "departmentId" field value
     * @param departmentId
     */
    public void setDepartmentId( Long departmentId ) {
        this.departmentId = departmentId ;
    }

    /**
     * Get the "departmentId" field value
     * @return the field value
     */
    public Long getDepartmentId() {
        return this.departmentId;
    }
    /**
     * Set the "departmentCode" field value
     * @param departmentCode
     */
    public void setDepartmentCode( String departmentCode ) {
        this.departmentCode = departmentCode ;
    }

    /**
     * Get the "departmentCode" field value
     * @return the field value
     */
    public String getDepartmentCode() {
        return this.departmentCode;
    }
    /**
     * Set the "departmentName" field value
     * @param departmentName
     */
    public void setDepartmentName( String departmentName ) {
        this.departmentName = departmentName ;
    }

    /**
     * Get the "departmentName" field value
     * @return the field value
     */
    public String getDepartmentName() {
        return this.departmentName;
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

    public Integer getStatusDepartment() {
        return statusDepartment;
    }

    public void setStatusDepartment(Integer statusDepartment) {
        this.statusDepartment = statusDepartment;
    }

}
