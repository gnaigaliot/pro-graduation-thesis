/*
 * Copyright (C) 2018 Viettel Telecom. All rights reserved. VIETTEL PROPRIETARY/CONFIDENTIAL. Use is
 * subject to license terms.
 */

package com.example.employeeManager.department.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.common.CommonUtil;
import com.example.common.DataTableResults;
import com.example.common.VfData;
import com.example.employeeManager.department.bean.DepartmentBean;
import com.example.employeeManager.department.bo.DepartmentBO;
import com.example.employeeManager.department.form.DepartmentForm;

@Transactional
@Repository
public interface DepartmentDAO extends CrudRepository<DepartmentBO, Long>
{
    /**
     * List all Department
     */
    public List<DepartmentBO> findAll();

    /**
     * get data by datatable
     * @param vfData
     * @param DepartmentForm
     * @return
     */
    public default DataTableResults<DepartmentBean> getDatatables(VfData vfData, DepartmentForm formData) {
        List<Object> paramList = new ArrayList<>();

        String sql = " SELECT ";
        sql += "        department_id As departmentId ";
        sql += "       ,department_code As departmentCode ";
        sql += "       ,department_name As departmentName ";
        sql += "       ,status_department As statusDepartment       ";
        sql += "       ,created_date As createdDate  ";
        sql += "       ,modified_date As modifiedDate ";
        sql += "       ,created_by As createdBy    ";
        sql += "       ,modified_by As modifiedBy   ";
        sql += "       FROM department d";

        StringBuilder strCondition = new StringBuilder(" WHERE 1 = 1 ");


        CommonUtil.filter(formData.getDepartmentId(), strCondition, paramList, "department_id");
        CommonUtil.filter(formData.getDepartmentCode(), strCondition, paramList, "department_code");
        CommonUtil.filter(formData.getDepartmentName(), strCondition, paramList, "department_name");
        CommonUtil.filter(formData.getStatusDepartment(), strCondition, paramList, "status");

        String orderBy = " ORDER BY departmentId DESC";
        return vfData.findPaginationQuery(sql + strCondition.toString(), orderBy, paramList, DepartmentBean.class);
    }
}
