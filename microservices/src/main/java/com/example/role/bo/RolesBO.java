package com.example.role.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RolesBO {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "role")
    private String role;

    @Column(name = "role_name")
    private String roleName;
    
    @Column(name = "description")
    private String description;


    /**
     * Set the "roleId" field value
     * @param roleId
     */
    public void setRoleId( Long roleId ) {
        this.roleId = roleId ;
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
    public void setRole( String role ) {
        this.role = role ;
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
    public void setRoleName( String roleName ) {
        this.roleName = roleName ;
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
