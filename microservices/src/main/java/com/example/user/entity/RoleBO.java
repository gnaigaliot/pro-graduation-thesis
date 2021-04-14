package com.example.user.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.common.CommonUtil;


/**
 * @author TanPTN
 * @since 09/05/2019
 * @version 1.0
 */
@Entity
@Table(name = "roles")
public class RoleBO  implements Serializable{

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "role")
    private String role;

    @Column(name = "role_name")
    private String roleName;

    public RoleBO() {
    }
    public RoleBO(RoleBO bo, String name) throws Exception {
        if(bo != null) {
            CommonUtil.copyProperties(this, bo);
        }
        this.roleName = name;
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
     * @return the roleName
     */
    public String getRoleName() {
        return roleName;
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
    
    /**
     * @param roleName the roleName to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    

}
