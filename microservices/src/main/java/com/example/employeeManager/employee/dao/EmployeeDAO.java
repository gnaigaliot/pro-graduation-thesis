/*
 * Copyright (C) 2018 Viettel Telecom. All rights reserved. VIETTEL PROPRIETARY/CONFIDENTIAL. Use is
 * subject to license terms.
 */

package com.example.employeeManager.employee.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.common.CommonUtil;
import com.example.common.DataTableResults;
import com.example.common.VfData;
import com.example.employeeManager.employee.bean.EmployeeBean;
import com.example.employeeManager.employee.bo.EmployeeBO;
import com.example.employeeManager.employee.form.EmployeeForm;

/**
 * @author d2tsoftware
 * @since 26/04/2021
 * @version 1.0
 */
@Transactional
@Repository
public interface EmployeeDAO extends CrudRepository<EmployeeBO, Long>
{
    /**
     * List all Employee
     */
    public List<EmployeeBO> findAll();

    /**
     * get data by datatable
     * @param vfData
     * @param EmployeeForm
     * @return
     */
    public default DataTableResults<EmployeeBean> getDatatables(VfData vfData, EmployeeForm formData) {
        List<Object> paramList = new ArrayList<>();

        String sql = " SELECT ";
        sql += "        employee_id As employeeId   ";
        sql += "       ,employee_code As employeeCode ";
        sql += "       ,employee_name As employeeName ";
        sql += "       ,date_of_birth As dateOfBirth  ";
        sql += "       ,gender As gender       ";
        sql += "       ,email As email        ";
        sql += "       ,phone_number As phoneNumber  ";
        sql += "       ,status As status       ";
        sql += "       ,created_date As createdDate  ";
        sql += "       ,modified_date As modifiedDate ";
        sql += "       ,created_by As createdBy    ";
        sql += "       ,modified_by As modifiedBy   ";
        sql += "       ,user_id As userId       ";
        sql += "       FROM employee ";

        StringBuilder strCondition = new StringBuilder(" WHERE 1 = 1 ");


        CommonUtil.filter(formData.getEmployeeId(), strCondition, paramList, "employee_id");
        CommonUtil.filter(formData.getEmployeeCode(), strCondition, paramList, "employee_code");
        CommonUtil.filter(formData.getEmployeeName(), strCondition, paramList, "employee_name");
        CommonUtil.filter(formData.getDateOfBirth(), strCondition, paramList, "date_of_birth");
        CommonUtil.filter(formData.getGender(), strCondition, paramList, "gender");
        CommonUtil.filter(formData.getEmail(), strCondition, paramList, "email");
        CommonUtil.filter(formData.getPhoneNumber(), strCondition, paramList, "phone_number");
        CommonUtil.filter(formData.getStatus(), strCondition, paramList, "status");
        CommonUtil.filter(formData.getUserId(), strCondition, paramList, "user_id");

        String orderBy = " ORDER BY employeeId DESC";
        return vfData.findPaginationQuery(sql + strCondition.toString(), orderBy, paramList, EmployeeBean.class);
    }
}
