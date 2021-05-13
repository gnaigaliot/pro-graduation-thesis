package com.example.user.dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.common.CommonUtil;
import com.example.common.DataTableResults;
import com.example.common.VfData;
import com.example.user.entity.UserBO;
import com.example.user.entity.UserBean;
import com.example.user.entity.UserForm;

@Transactional
@Repository
public interface UserDAO extends CrudRepository<UserBO, Long> {
    UserBO findByUserName(String username);
    
    public default UserBean getUserWithRole(VfData uttData, String userName) {
        String hql = "SELECT " 
                + "     usr.user_id AS userId " 
                + "     , usr.user_name as userName " 
                + "     , usr.password as password "
                + "     , rls.role as role"
                + " FROM users usr "
                + " INNER JOIN roles rls ON usr.role_id = rls.role_id "
                + " WHERE 1 = 1 AND LOWER(usr.user_name) = :username";
        SQLQuery query = uttData.createSQLQuery(hql);
        query.setParameter("username", userName.toLowerCase());
        uttData.setResultTransformer(query, UserBean.class);
        return (UserBean) query.uniqueResult();
    }
    
    public default UserBean getUserByName(VfData uttData, String userName) {
        String hql = "SELECT " 
                + "     usr.user_id AS userId " 
                + "     , usr.user_name as userName " 
                + "     , usr.password as password "
                + "     ,usr.full_name as fullName "
                + "     ,usr.gender as gender "
                + "     ,usr.date_of_birth AS dateOfBirth "
                + "     ,usr.email AS email "
                + "     ,usr.mobile_number AS mobileNumber "
                + "     , rls.role as role"
                + "     , rls.role_id as roleId"
                + "     , rls.role_name as roleName"
                + "     , e.employee_img_url as employeeImgUrl "
                + " FROM users usr "
                + " LEFT JOIN employee_images e ON usr.employee_id = e.employee_id "
                + " INNER JOIN roles rls ON usr.role_id = rls.role_id "
                + " WHERE 1 = 1 AND LOWER(usr.user_name) = :username";
        SQLQuery query = uttData.createSQLQuery(hql);
        query.setParameter("username", userName.toLowerCase());
        uttData.setResultTransformer(query, UserBean.class);
        return (UserBean) query.uniqueResult();
    }
    
    /**
     * get data by datatable
     * @param uttData
     * @return
     */
    
    public default DataTableResults<UserBean> getDatatable(VfData uttData, UserForm userForm, HttpServletRequest req) {
        List<Object> paramList = new ArrayList<>();
        String nativeSQL = "SELECT "
                + "     usr.user_id AS userId " 
                + "     , usr.user_name as userName " 
                + "     , usr.password as password "
                + "     , usr.full_name as fullName "
                + "     , usr.gender as gender "
                + "     , usr.date_of_birth AS dateOfBirth "
                + "     , usr.email AS email "
                + "     , usr.mobile_number AS mobileNumber "
                + "     , usr.employee_id As employeeId "
                + "     , rls.role as role"
                + "     , rls.role_name as roleName"
                + " FROM users usr "
                + " INNER JOIN roles rls ON usr.role_id = rls.role_id ";

        StringBuilder strCondition = new StringBuilder(" WHERE 1 = 1");
        
        CommonUtil.filter(userForm.getUserName(), strCondition, paramList, "usr.user_name");
        CommonUtil.filter(userForm.getFullName(), strCondition, paramList, "usr.full_name");

        String orderBy = " ORDER BY userId DESC ";
        return uttData.findPaginationQuery(nativeSQL + strCondition.toString(), orderBy, paramList, UserBean.class);
    }


    public default UserBean getUserInfoById(VfData uttData, Long userId) {
        String hql = "SELECT " 
                + "     usr.user_id AS userId " 
                + "     , usr.user_name as userName " 
                + "     , usr.password as password "
                + "     ,usr.full_name as fullName "
                + "     ,usr.gender as gender "
                + "     ,usr.date_of_birth AS dateOfBirth "
                + "     ,usr.email AS email "
                + "     ,usr.mobile_number AS mobileNumber "
                + "     , rls.role as role"
                + "     , rls.role_name as roleName"
                + " FROM users usr "
                + " INNER JOIN roles rls ON usr.role_id = rls.role_id "
                + " WHERE 1 = 1 AND usr.user_id = :userId";
        SQLQuery query = uttData.createSQLQuery(hql);
        query.setParameter("userId", userId);
        uttData.setResultTransformer(query, UserBean.class);
        return (UserBean) query.uniqueResult();
    }
}
