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
        sql += "        e.employee_id As employeeId   ";
        sql += "       ,e.employee_code As employeeCode ";
        sql += "       ,e.employee_name As employeeName ";
        sql += "       ,e.date_of_birth As dateOfBirth  ";
        sql += "       ,e.gender As gender       ";
        sql += "       ,e.email As email        ";
        sql += "       ,e.phone_number As phoneNumber  ";
        sql += "       ,e.status As status       ";
        sql += "       ,e.created_date As createdDate  ";
        sql += "       ,e.modified_date As modifiedDate ";
        sql += "       ,e.created_by As createdBy    ";
        sql += "       ,e.modified_by As modifiedBy   ";
        sql += "       ,e.user_id As userId       ";
        sql += "       ,e.department_id As departmentId ";
        sql += "       ,e.position_id As positionId   ";
        sql += "       ,p.position_name As positionName   ";
        sql += "       ,d.department_name As departmentName   ";
        sql += "       FROM employee e ";
        sql += "       LEFT JOIN positions p ON e.position_id = p.position_id ";
        sql += "       LEFT JOIN department d ON e.department_id = d.department_id ";

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
