package com.example.user.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * UserBean
 *
 * @author
 * @since 1.0
 * @version 1.0
 */
public class UserBean implements UserDetails{

    private Long       userId;
    private String     userName;
    private String     password;
    private String     fullName;
    private String     dateOfBirth;
    private Long       gender;
    private String     email;
    private String     mobileNumber;
    private Long       positionId;
    private Long       roleId;
    private String     className;
    private String     majorName;
    private String     departmentName;
    private String     userCode;
    private String     role;
    private String     roleName;
    private List<String>     lstRoleCode;
    
    @Override
    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
          authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
      }
    
    
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        // TODO Auto-generated method stub
//        return listGrantedAuthoritys;
//    }
    
    


	@Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return password;
    }
    
    public List<String> getLstRoleCode() {
		return lstRoleCode;
	}


	public void setLstRoleCode(List<String> lstRoleCode) {
		this.lstRoleCode = lstRoleCode;
	}


	@Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    
    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    
    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    

    
    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    
    /**
     * @return the gender
     */
    public Long getGender() {
        return gender;
    }

    
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    
    /**
     * @return the mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    
    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    
    /**
     * @return the majorName
     */
    public String getMajorName() {
        return majorName;
    }

    
    /**
     * @return the departmentName
     */
    public String getDepartmentName() {
        return departmentName;
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

    
    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    
    /**
     * @param gender the gender to set
     */
    public void setGender(Long gender) {
        this.gender = gender;
    }

    
    
    /**
     * @return the dateOfBirth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    
    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    
    /**
     * @param mobileNumber the mobileNumber to set
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    
    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }

    
    /**
     * @param majorName the majorName to set
     */
    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    
    /**
     * @param departmentName the departmentName to set
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    
    /**
     * @param userCode the userCode to set
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    

    
    /**
     * @return the roleId
     */
    public Long getRoleId() {
        return roleId;
    }

    
    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    
    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    
    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

    
    /**
     * @return the roleName
     */
    public String getRoleName() {
        return roleName;
    }

    
    /**
     * @param roleName the roleName to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    
    /**
     * @return the postisionId
     */
    public Long getPositionId() {
        return positionId;
    }

    
    /**
     * @param postisionId the postisionId to set
     */
    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }
    
}
