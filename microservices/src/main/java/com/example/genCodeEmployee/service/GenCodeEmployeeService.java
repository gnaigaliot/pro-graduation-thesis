package com.example.genCodeEmployee.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.common.DataTableResults;
import com.example.common.VfData;
import com.example.genCodeEmployee.bean.GenCodeEmployeeBean;
import com.example.genCodeEmployee.bo.GenCodeEmployeeBO;
import com.example.genCodeEmployee.dao.GenCodeEmployeeDAO;
import com.example.genCodeEmployee.form.GenCodeEmployeeForm;

@Service
public class GenCodeEmployeeService {

    @Autowired
    private GenCodeEmployeeDAO genCodeEmployeedao;
    @Autowired
    private VfData vfData;

    /**
     * findById
     * @param GenCodeEmployeeId
     * @return
     */
    public GenCodeEmployeeBO findById(Long GenCodeEmployeeId) {
        return genCodeEmployeedao.findById(GenCodeEmployeeId).orElse(null);
    }

    /**
     * getDatatables
     * @param genCodeEmployeeForm
     * @return
     */
    public DataTableResults<GenCodeEmployeeBean> getDatatables(GenCodeEmployeeForm genCodeEmployeeForm) {
        return genCodeEmployeedao.getDatatables(vfData, genCodeEmployeeForm);
    }

    /**
     * saveOrUpdate
     * @param entity
     */
    @Transactional
    public void saveOrUpdate(GenCodeEmployeeBO entity) {
        genCodeEmployeedao.save(entity);
    }

    /**
     * delete
     * @param entity
     */
    public void delete(GenCodeEmployeeBO entity) {
        genCodeEmployeedao.delete(entity);
    }
    
    public Long getLastCodeNumberEmployee() {
        GenCodeEmployeeBean bean = genCodeEmployeedao.getLastCodeNumberEmployee(vfData);
        if (bean != null) {
            return bean.getIdCode();
        } else {
            return 0L;
        }
    }
}
