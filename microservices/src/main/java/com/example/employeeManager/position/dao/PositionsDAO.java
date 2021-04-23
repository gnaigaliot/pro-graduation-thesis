/*
 * Copyright (C) 2018 Viettel Telecom. All rights reserved. VIETTEL PROPRIETARY/CONFIDENTIAL. Use is
 * subject to license terms.
 */

package com.example.employeeManager.position.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.common.CommonUtil;
import com.example.common.DataTableResults;
import com.example.common.VfData;
import com.example.employeeManager.position.bean.PositionsBean;
import com.example.employeeManager.position.bo.PositionsBO;
import com.example.employeeManager.position.form.PositionsForm;

/**
 * @author d2tsoftware
 * @since 23/04/2021
 * @version 1.0
 */
@Transactional
@Repository
public interface PositionsDAO extends CrudRepository<PositionsBO, Long>
{
    /**
     * List all Positions
     */
    public List<PositionsBO> findAll();

    /**
     * get data by datatable
     * @param vfData
     * @param PositionsForm
     * @return
     */
    public default DataTableResults<PositionsBean> getDatatables(VfData vfData, PositionsForm formData) {
        List<Object> paramList = new ArrayList<>();

        String sql = " SELECT ";
        sql += "        position_id As positionId   ";
        sql += "       ,position_code As positionCode ";
        sql += "       ,position_name As positionName ";
        sql += "       ,salary As salary       ";
        sql += "       ,status As status       ";
        sql += "       ,created_date As createdDate  ";
        sql += "       ,modified_date As modifiedDate ";
        sql += "       ,created_by As createdBy    ";
        sql += "       ,modified_by As modifiedBy   ";
        sql += "       FROM positions ";

        StringBuilder strCondition = new StringBuilder(" WHERE 1 = 1 ");


        CommonUtil.filter(formData.getPositionId(), strCondition, paramList, "position_id");
        CommonUtil.filter(formData.getPositionCode(), strCondition, paramList, "position_code");
        CommonUtil.filter(formData.getPositionName(), strCondition, paramList, "position_name");
        CommonUtil.filter(formData.getSalary(), strCondition, paramList, "salary");
        CommonUtil.filter(formData.getStatus(), strCondition, paramList, "status");

        String orderBy = " ORDER BY positionId DESC";
        return vfData.findPaginationQuery(sql + strCondition.toString(), orderBy, paramList, PositionsBean.class);
    }
}
