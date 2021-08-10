package com.example.employeeManager.employee.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.common.DataTableResults;
import com.example.common.VfData;
import com.example.employeeManager.employee.bean.EmployeeBean;
import com.example.employeeManager.employee.bo.EmployeeBO;
import com.example.employeeManager.employee.dao.EmployeeDAO;
import com.example.employeeManager.employee.form.EmployeeForm;
import com.example.report.PieChartBean;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDAO employeedao;
    @Autowired
    private VfData vfData;

    /**
     * findById
     * 
     * @param EmployeeId
     * @return
     */
    public EmployeeBO findById(Long EmployeeId) {
        return employeedao.findById(EmployeeId).orElse(null);
    }

    /**
     * getDatatables
     * 
     * @param employeeForm
     * @return
     */
    public DataTableResults<EmployeeBean> getDatatables(EmployeeForm employeeForm) {
        return employeedao.getDatatables(vfData, employeeForm);
    }

    /**
     * saveOrUpdate
     * 
     * @param entity
     */
    @Transactional
    public void saveOrUpdate(EmployeeBO entity) {
        employeedao.save(entity);
    }

    /**
     * delete
     * 
     * @param entity
     */
    public void delete(EmployeeBO entity) {
        employeedao.delete(entity);
    }
    
    public List<EmployeeBO> findAll() {
        return employeedao.findAll();
    }
    
    public List<PieChartBean> getPieChartData() {
        return employeedao.getPieChartData(vfData);
    }
    
    public List<EmployeeBean> getListActiveEmployee() {
        return employeedao.getListActiveEmployee(vfData);
    }
}
