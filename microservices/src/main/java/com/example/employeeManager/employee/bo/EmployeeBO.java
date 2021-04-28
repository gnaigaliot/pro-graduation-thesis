package com.example.employeeManager.employee.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class EmployeeBO {

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(name = "employee_code")
    private String employeeCode;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "status")
    private Integer status;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "modified_date")
    private Date modifiedDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "position_id")
    private Long positionId;
    
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
     * Set the "employeeCode" field value
     * @param employeeCode
     */
    public void setEmployeeCode( String employeeCode ) {
        this.employeeCode = employeeCode ;
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
    public void setEmployeeName( String employeeName ) {
        this.employeeName = employeeName ;
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
    public void setDateOfBirth( Date dateOfBirth ) {
        this.dateOfBirth = dateOfBirth ;
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
    public void setGender( Integer gender ) {
        this.gender = gender ;
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
    public void setEmail( String email ) {
        this.email = email ;
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
    public void setPhoneNumber( String phoneNumber ) {
        this.phoneNumber = phoneNumber ;
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
    public void setStatus( Integer status ) {
        this.status = status ;
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
    /**
     * Set the "userId" field value
     * @param userId
     */
    public void setUserId( Long userId ) {
        this.userId = userId ;
    }

    /**
     * Get the "userId" field value
     * @return the field value
     */
    public Long getUserId() {
        return this.userId;
    }
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
}
