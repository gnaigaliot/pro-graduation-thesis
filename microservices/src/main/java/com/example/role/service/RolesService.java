package com.example.role.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.common.DataTableResults;
import com.example.common.VfData;
import com.example.role.bean.RolesBean;
import com.example.role.bo.RolesBO;
import com.example.role.dao.RolesDAO;
import com.example.role.form.RolesForm;

@Service
public class RolesService {

    @Autowired
    private RolesDAO rolesdao;
    @Autowired
    private VfData vfData;

    /**
     * findById
     * @param RolesId
     * @return
     */
    public RolesBO findById(Long RolesId) {
        return rolesdao.findById(RolesId).orElse(null);
    }

    /**
     * getDatatables
     * @param rolesForm
     * @return
     */
    public DataTableResults<RolesBean> getDatatables(RolesForm rolesForm) {
        return rolesdao.getDatatables(vfData, rolesForm);
    }

    /**
     * saveOrUpdate
     * @param entity
     */
    @Transactional
    public void saveOrUpdate(RolesBO entity) {
        rolesdao.save(entity);
    }

    /**
     * delete
     * @param entity
     */
    public void delete(RolesBO entity) {
        rolesdao.delete(entity);
    }
}
