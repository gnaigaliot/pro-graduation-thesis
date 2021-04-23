/*
 * Copyright (C) 2018 Viettel Telecom. All rights reserved. VIETTEL PROPRIETARY/CONFIDENTIAL. Use is
 * subject to license terms.
 */

package com.example.employeeManager.position.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.common.DataTableResults;
import com.example.common.VfData;
import com.example.employeeManager.position.bean.PositionsBean;
import com.example.employeeManager.position.form.PositionsForm;
import com.example.employeeManager.position.bo.PositionsBO;
import com.example.employeeManager.position.dao.PositionsDAO;

/**
 * @author d2tsoftware
 * @since 23/04/2021
 * @version 1.0
 */
@Service
public class PositionsService {

    @Autowired
    private PositionsDAO positionsdao;
    @Autowired
    private VfData vfData;

    /**
     * findById
     * @param PositionsId
     * @return
     */
    public PositionsBO findById(Long PositionsId) {
        return positionsdao.findById(PositionsId).orElse(null);
    }

    /**
     * getDatatables
     * @param positionsForm
     * @return
     */
    public DataTableResults<PositionsBean> getDatatables(PositionsForm positionsForm) {
        return positionsdao.getDatatables(vfData, positionsForm);
    }

    /**
     * saveOrUpdate
     * @param entity
     */
    @Transactional
    public void saveOrUpdate(PositionsBO entity) {
        positionsdao.save(entity);
    }

    /**
     * delete
     * @param entity
     */
    public void delete(PositionsBO entity) {
        positionsdao.delete(entity);
    }
}
