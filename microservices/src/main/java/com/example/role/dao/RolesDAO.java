package com.example.role.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.common.CommonUtil;
import com.example.common.DataTableResults;
import com.example.common.VfData;
import com.example.role.bean.RolesBean;
import com.example.role.bo.RolesBO;
import com.example.role.form.RolesForm;

@Transactional
@Repository
public interface RolesDAO extends CrudRepository<RolesBO, Long>
{
    /**
     * List all Roles
     */
    public List<RolesBO> findAll();

    /**
     * get data by datatable
     * @param vfData
     * @param RolesForm
     * @return
     */
    public default DataTableResults<RolesBean> getDatatables(VfData vfData, RolesForm formData) {
        List<Object> paramList = new ArrayList<>();

        String sql = " SELECT ";
        sql += "        role_id As roleId       ";
        sql += "       ,role As role         ";
        sql += "       ,role_name As roleName     ";
        sql += "       ,description As description     ";
        sql += "       FROM roles ";

        StringBuilder strCondition = new StringBuilder(" WHERE 1 = 1 ");


        CommonUtil.filter(formData.getRoleId(), strCondition, paramList, "role_id");
        CommonUtil.filter(formData.getRole(), strCondition, paramList, "role");
        CommonUtil.filter(formData.getRoleName(), strCondition, paramList, "role_name");

        String orderBy = " ORDER BY roleId DESC";
        return vfData.findPaginationQuery(sql + strCondition.toString(), orderBy, paramList, RolesBean.class);
    }
}
