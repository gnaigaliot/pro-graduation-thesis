package com.example.role.bean;

public class RolesBean {

    private Long       roleId;
    private String     role;
    private String     roleName;
    private String description;

    /**
     * Set the "roleId" field value
     * @param roleId
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * Get the "roleId" field value
     * @return the field value
     */
    public Long getRoleId() {
        return this.roleId;
    }


    /**
     * Set the "role" field value
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Get the "role" field value
     * @return the field value
     */
    public String getRole() {
        return this.role;
    }


    /**
     * Set the "roleName" field value
     * @param roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Get the "roleName" field value
     * @return the field value
     */
    public String getRoleName() {
        return this.roleName;
    }

    
    public String getDescription() {
        return description;
    }

    
    public void setDescription(String description) {
        this.description = description;
    }
}
