package com.example.user.entity;

import java.util.Date;
import java.util.List;

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
    private List<Long> lstRoleId;
    private String employeeImgUrl;
    private String positionName;
    private String departmentName;
    private String employeeCode;

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public Long getPositionId() {
        return positionId;
    }
    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }
    public Long getGender() {
        return gender;
    }
    public void setGender(Long gender) {
        this.gender = gender;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public Long getMajorId() {
        return majorId;
    }
    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }
    public Long getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
    public String getUserCode() {
        return userCode;
    }
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getSemester() {
        return semester;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }
    public Long getRoleId() {
        return roleId;
    }
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    public List<Long> getLstRoleId() {
        return lstRoleId;
    }
    public void setLstRoleId(List<Long> lstRoleId) {
        this.lstRoleId = lstRoleId;
    }
    public String getEmployeeImgUrl() {
        return employeeImgUrl;
    }
    public void setEmployeeImgUrl(String employeeImgUrl) {
        this.employeeImgUrl = employeeImgUrl;
    }
    public String getPositionName() {
        return positionName;
    }
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public String getEmployeeCode() {
        return employeeCode;
    }
    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }
}
