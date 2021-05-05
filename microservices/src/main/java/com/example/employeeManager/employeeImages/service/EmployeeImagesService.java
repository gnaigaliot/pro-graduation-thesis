package com.example.employeeManager.employeeImages.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.common.DataTableResults;
import com.example.common.VfData;
import com.example.employeeManager.employeeImages.bean.EmployeeImagesBean;
import com.example.employeeManager.employeeImages.bo.EmployeeImagesBO;
import com.example.employeeManager.employeeImages.dao.EmployeeImagesDAO;
import com.example.employeeManager.employeeImages.form.EmployeeImagesForm;

@Service
public class EmployeeImagesService {

    @Autowired
    private EmployeeImagesDAO employeeImagesdao;
    @Autowired
    private VfData vfData;

    /**
     * findById
     * @param EmployeeImagesId
     * @return
     */
    public EmployeeImagesBO findById(Long EmployeeImagesId) {
        return employeeImagesdao.findById(EmployeeImagesId).orElse(null);
    }

    /**
     * getDatatables
     * @param employeeImagesForm
     * @return
     */
    public DataTableResults<EmployeeImagesBean> getDatatables(EmployeeImagesForm employeeImagesForm) {
        return employeeImagesdao.getDatatables(vfData, employeeImagesForm);
    }

    /**
     * saveOrUpdate
     * @param entity
     */
    @Transactional
    public void saveOrUpdate(EmployeeImagesBO entity) {
        employeeImagesdao.save(entity);
    }

    /**
     * delete
     * @param entity
     */
    public void delete(EmployeeImagesBO entity) {
        employeeImagesdao.delete(entity);
    }
    
    public void saveAll(List<EmployeeImagesBO> listEmployeeImages) {
        employeeImagesdao.saveAll(listEmployeeImages);
    }
    
    public List<String> getListImgUrlByEmplId(Long employeeId) {
        return employeeImagesdao.getUrlImageByEmployeeId(vfData, employeeId);
    }
}
