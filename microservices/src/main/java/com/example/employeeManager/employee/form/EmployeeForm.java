package com.example.employeeManager.employee.form;

import java.util.Date;

/**
 * EmployeeForm class
 *
 * @author
 * @since 1.0
 * @version 1.0
 */
public class EmployeeForm {

    private Long       employeeId;
    private String     employeeCode;
    private String     employeeName;
    private Date       dateOfBirth;
    private Integer    gender;
    private String     email;
    private String     phoneNumber;
    private Integer    status;
    private Date       createdDate;
    private Date       modifiedDate;
    private String     createdBy;
    private String     modifiedBy;
    private Long       userId;


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
     * Set the "employeeCode" field value
     * @param employeeCode
     */
    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    /**
     * Get the "employeeCode" field value
     * @return the field value
     */
    public String getEmployeeCode() {
        return this.employeeCode;
    }


    /**
     * Set the "employeeName" field value
     * @param employeeName
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * Get the "employeeName" field value
     * @return the field value
     */
    public String getEmployeeName() {
        return this.employeeName;
    }


    /**
     * Set the "dateOfBirth" field value
     * @param dateOfBirth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Get the "dateOfBirth" field value
     * @return the field value
     */
    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }


    /**
     * Set the "gender" field value
     * @param gender
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * Get the "gender" field value
     * @return the field value
     */
    public Integer getGender() {
        return this.gender;
    }


    /**
     * Set the "email" field value
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the "email" field value
     * @return the field value
     */
    public String getEmail() {
        return this.email;
    }


    /**
     * Set the "phoneNumber" field value
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the "phoneNumber" field value
     * @return the field value
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }


    /**
     * Set the "status" field value
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Get the "status" field value
     * @return the field value
     */
    public Integer getStatus() {
        return this.status;
    }


    /**
     * Set the "createdDate" field value
     * @param createdDate
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
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
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * Get the "modifiedBy" field value
     * @return the field value
     */
    public String getModifiedBy() {
        return this.modifiedBy;
    }


    /**
     * Set the "userId" field value
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Get the "userId" field value
     * @return the field value
     */
    public Long getUserId() {
        return this.userId;
    }

}
