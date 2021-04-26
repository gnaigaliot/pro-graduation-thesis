/*
 * Copyright (C) 2018 Viettel Telecom. All rights reserved. VIETTEL PROPRIETARY/CONFIDENTIAL. Use is
 * subject to license terms.
 */

package com.example.employeeManager.department.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.common.DataTableResults;
import com.example.common.VfData;
import com.example.employeeManager.department.bean.DepartmentBean;
import com.example.employeeManager.department.bo.DepartmentBO;
import com.example.employeeManager.department.dao.DepartmentDAO;
import com.example.employeeManager.department.form.DepartmentForm;

/**
 * @author d2tsoftware
 * @since 24/04/2021
 * @version 1.0
 */
@Service
public class DepartmentService {

    @Autowired
    private DepartmentDAO departmentdao;
    @Autowired
    private VfData vfData;

    /**
     * findById
     * @param DepartmentId
     * @return
     */
    public DepartmentBO findById(Long DepartmentId) {
        return departmentdao.findById(DepartmentId).orElse(null);
    }

    /**
     * getDatatables
     * @param departmentForm
     * @return
     */
    public DataTableResults<DepartmentBean> getDatatables(DepartmentForm departmentForm) {
        return departmentdao.getDatatables(vfData, departmentForm);
    }

    /**
     * saveOrUpdate
     * @param entity
     */
    @Transactional
    public void saveOrUpdate(DepartmentBO entity) {
        departmentdao.save(entity);
    }

    /**
     * delete
     * @param entity
     */
    public void delete(DepartmentBO entity) {
        departmentdao.delete(entity);
    }
}
