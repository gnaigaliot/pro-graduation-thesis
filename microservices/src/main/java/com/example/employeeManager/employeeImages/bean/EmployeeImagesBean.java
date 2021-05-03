/*
 * Copyright (C) 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.employeeManager.employeeImages.bean;


/**
 * EmployeeImagesBean for table "employee_images"
 *
 * @author
 * @since 1.0
 * @version 1.0
 */
public class EmployeeImagesBean {

    private Long       employeeImageId;
    private Long       employeeId;
    private String     employeeImgUrl;


    /**
     * Set the "employeeImageId" field value
     * @param employeeImageId
     */
    public void setEmployeeImageId(Long employeeImageId) {
        this.employeeImageId = employeeImageId;
    }

    /**
     * Get the "employeeImageId" field value
     * @return the field value
     */
    public Long getEmployeeImageId() {
        return this.employeeImageId;
    }


    /**
     * Set the "employeeId" field value
     * @param employeeId
     */
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Get the "employeeId" field value
     * @return the field value
     */
    public Long getEmployeeId() {
        return this.employeeId;
    }


    /**
     * Set the "employeeImgUrl" field value
     * @param employeeImgUrl
     */
    public void setEmployeeImgUrl(String employeeImgUrl) {
        this.employeeImgUrl = employeeImgUrl;
    }

    /**
     * Get the "employeeImgUrl" field value
     * @return the field value
     */
    public String getEmployeeImgUrl() {
        return this.employeeImgUrl;
    }

}
