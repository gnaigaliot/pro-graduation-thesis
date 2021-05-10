package com.example.employeeManager.employeeImages.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.common.CommonUtil;
import com.example.common.DataTableResults;
import com.example.common.VfData;
import com.example.employeeManager.employeeImages.bean.EmployeeImagesBean;
import com.example.employeeManager.employeeImages.bo.EmployeeImagesBO;
import com.example.employeeManager.employeeImages.form.EmployeeImagesForm;

/**
 * @author d2tsoftware
 * @since 04/05/2021
 * @version 1.0
 */
@Transactional
@Repository
public interface EmployeeImagesDAO extends CrudRepository<EmployeeImagesBO, Long>
{
    /**
     * List all EmployeeImages
     */
    public List<EmployeeImagesBO> findAll();

    /**
     * get data by datatable
     * @param vfData
     * @param EmployeeImagesForm
     * @return
     */
    public default DataTableResults<EmployeeImagesBean> getDatatables(VfData vfData, EmployeeImagesForm formData) {
        List<Object> paramList = new ArrayList<>();

        String sql = " SELECT ";
        sql += "        employee_image_id As employeeImageId ";
        sql += "       ,employee_id As employeeId   ";
        sql += "       ,employee_img_url As employeeImgUrl ";
        sql += "       FROM employee_images ";

        StringBuilder strCondition = new StringBuilder(" WHERE 1 = 1 ");


        CommonUtil.filter(formData.getEmployeeImageId(), strCondition, paramList, "employee_image_id");
        CommonUtil.filter(formData.getEmployeeId(), strCondition, paramList, "employee_id");
        CommonUtil.filter(formData.getEmployeeImgUrl(), strCondition, paramList, "employee_img_url");

        String orderBy = " ORDER BY employeeImageId DESC";
        return vfData.findPaginationQuery(sql + strCondition.toString(), orderBy, paramList, EmployeeImagesBean.class);
    }
    
    public default EmployeeImagesBO getUrlImageByEmployeeId(VfData vfData, Long employeeId) {
        String sql = " SELECT "
                + " ei.employee_img_url "
                + " FROM employee_images ei "
                + " WHERE 1=1 AND ei.employee_id = :employeeId ";
        SQLQuery query = vfData.createSQLQuery(sql);
        query.setParameter("employeeId", employeeId);
        vfData.setResultTransformer(query, EmployeeImagesBO.class);
        return (EmployeeImagesBO) query.uniqueResult();
    }
}
