package com.example.user.entity;

import java.util.Date;
import java.util.List;

/**
 * UserForm class
 *
 * @author
 * @since 1.0
 * @version 1.0
 */
public class UserForm {

    private Long       userId;
    private String     userName;
    private String     password;
    private String     fullName;
    private Date       dateOfBirth;
    private Long       positionId;
    private Long       gender;
    private String     className;
    private String     email;
    private String     mobileNumber;
    private String     course;
    private Long       majorId;
    private Long       departmentId;
    private String     userCode;
    private String     year;
    private String     semester;
    private Long       roleId;
    private  List<Long> lstRoleId;
    
//    /**
//     * @return the userId
//     */
//    public Long getUserId() {
//        return userId;
//    }
    
    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }
    
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    
//    /**
//     * @return the fullName
//     */
//    public String getFullName() {
//        return fullName;
//    }
//    
//    /**
//     * @return the dateOfBirth
//     */
//    public Date getDateOfBirth() {
//        return dateOfBirth;
//    }
//    
//    /**
//     * @return the gender
//     */
//    public Long getGender() {
//        return gender;
//    }
//    
//    /**
//     * @return the email
//     */
//    public String getEmail() {
//        return email;
//    }
//    
//    /**
//     * @return the mobileNumber
//     */
//    public String getMobileNumber() {
//        return mobileNumber;
//    }
//    
//    /**
//     * @return the positionId
//     */
//    public Long getPositionId() {
//        return positionId;
//    }
//    
//    /**
//     * @return the className
//     */
//    public String getClassName() {
//        return className;
//    }
//    
//    /**
//     * @return the major
//     */
//    public String getMajor() {
//        return major;
//    }
//    
//    /**
//     * @return the department
//     */
//    public String getDepartment() {
//        return department;
//    }
//    
//    /**
//     * @return the userCode
//     */
//    public String getUserCode() {
//        return userCode;
//    }
//    
//    /**
//     * @param userId the userId to set
//     */
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//    
    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
//    
//    /**
//     * @param fullName the fullName to set
//     */
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }
//    
//    /**
//     * @param dateOfBirth the dateOfBirth to set
//     */
//    public void setDateOfBirth(Date dateOfBirth) {
//        this.dateOfBirth = dateOfBirth;
//    }
//    
//    /**
//     * @param gender the gender to set
//     */
//    public void setGender(Long gender) {
//        this.gender = gender;
//    }
//    
//    /**
//     * @param email the email to set
//     */
//    public void setEmail(String email) {
//        this.email = email;
//    }
//    
//    /**
//     * @param mobileNumber the mobileNumber to set
//     */
//    public void setMobileNumber(String mobileNumber) {
//        this.mobileNumber = mobileNumber;
//    }
//    
//    /**
//     * @param positionId the positionId to set
//     */
//    public void setPositionId(Long positionId) {
//        this.positionId = positionId;
//    }
//    
//    /**
//     * @param className the className to set
//     */
//    public void setClassName(String className) {
//        this.className = className;
//    }
//    
//    /**
//     * @param major the major to set
//     */
//    public void setMajor(String major) {
//        this.major = major;
//    }
//    
//    /**
//     * @param department the department to set
//     */
//    public void setDepartment(String department) {
//        this.department = department;
//    }
//    
//    /**
//     * @param userCode the userCode to set
//     */
//    public void setUserCode(String userCode) {
//        this.userCode = userCode;
//    }

    
    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    
    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    
    /**
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    
    /**
     * @return the positionId
     */
    public Long getPositionId() {
        return positionId;
    }

    
    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    
    /**
     * @return the course
     */
    public String getCourse() {
        return course;
    }

    
    /**
     * @return the majorId
     */
    public Long getMajorId() {
        return majorId;
    }

    
    /**
     * @return the departmentId
     */
    public Long getDepartmentId() {
        return departmentId;
    }

    
    /**
     * @return the userCode
     */
    public String getUserCode() {
        return userCode;
    }

    
    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    
    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    
    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    
    /**
     * @param positionId the positionId to set
     */
    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    
    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }

    
    /**
     * @param course the course to set
     */
    public void setCourse(String course) {
        this.course = course;
    }

    
    /**
     * @param majorId the majorId to set
     */
    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    
    /**
     * @param departmentId the departmentId to set
     */
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    
    /**
     * @param userCode the userCode to set
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    
    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    
    
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the semester
     */
    public String getSemester() {
        return semester;
    }

    
    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    
    /**
     * @param semester the semester to set
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    
    /**
     * @return the gender
     */
    public Long getGender() {
        return gender;
    }

    
    /**
     * @param gender the gender to set
     */
    public void setGender(Long gender) {
        this.gender = gender;
    }

    
    /**
     * @return the mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    
    /**
     * @param mobileNumber the mobileNumber to set
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    
    /**
     * @return the roleId
     */
    public Long getRoleId() {
        return roleId;
    }

    
    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

	public List<Long> getLstRoleId() {
		return lstRoleId;
	}

	public void setLstRoleId(List<Long> lstRoleId) {
		this.lstRoleId = lstRoleId;
	}
}
