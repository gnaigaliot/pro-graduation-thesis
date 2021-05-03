/*
 * Copyright (C) 2018 Viettel Telecom. All rights reserved. VIETTEL PROPRIETARY/CONFIDENTIAL. Use is
 * subject to license terms.
 */

package com.example.employeeManager.employeeImages.bo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_images")
public class EmployeeImagesBO {

    @Id
    @Column(name = "employee_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeImageId;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "employee_img_url")
    private String employeeImgUrl;


    /**
     * Set the "employeeImageId" field value
     * @param employeeImageId
     */
    public void setEmployeeImageId( Long employeeImageId ) {
        this.employeeImageId = employeeImageId ;
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
    public void setEmployeeId( Long employeeId ) {
        this.employeeId = employeeId ;
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
    public void setEmployeeImgUrl( String employeeImgUrl ) {
        this.employeeImgUrl = employeeImgUrl ;
    }

    /**
     * Get the "employeeImgUrl" field value
     * @return the field value
     */
    public String getEmployeeImgUrl() {
        return this.employeeImgUrl;
    }

}
