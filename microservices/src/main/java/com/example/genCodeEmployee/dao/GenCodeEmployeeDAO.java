package com.example.genCodeEmployee.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.common.CommonUtil;
import com.example.common.DataTableResults;
import com.example.common.VfData;
import com.example.genCodeEmployee.bean.GenCodeEmployeeBean;
import com.example.genCodeEmployee.bo.GenCodeEmployeeBO;
import com.example.genCodeEmployee.form.GenCodeEmployeeForm;

@Transactional
@Repository
public interface GenCodeEmployeeDAO extends CrudRepository<GenCodeEmployeeBO, Long>
{
    /**
     * List all GenCodeEmployee
     */
    public List<GenCodeEmployeeBO> findAll();

    /**
     * get data by datatable
     * @param vfData
     * @param GenCodeEmployeeForm
     * @return
     */
    public default DataTableResults<GenCodeEmployeeBean> getDatatables(VfData vfData, GenCodeEmployeeForm formData) {
        List<Object> paramList = new ArrayList<>();

        String sql = " SELECT ";
        sql += "        gen_code_id As genCodeId    ";
        sql += "       ,id_code As idCode      ";
        sql += "       FROM gen_code_employee ";

        StringBuilder strCondition = new StringBuilder(" WHERE 1 = 1 ");


        CommonUtil.filter(formData.getGenCodeId(), strCondition, paramList, "gen_code_id");
        CommonUtil.filter(formData.getIdCode(), strCondition, paramList, "id_code");

        String orderBy = " ORDER BY id_code DESC";
        return vfData.findPaginationQuery(sql + strCondition.toString(), orderBy, paramList, GenCodeEmployeeBean.class);
    }
    
    public default GenCodeEmployeeBean getLastCodeNumberEmployee(VfData vfData) {
        String sql = " SELECT "
                + " gce.id_code As idCode, "
                + " gce.gen_code_id As genCodeId "
                + " FROM gen_code_employee gce "
                + " WHERE 1=1 "
                + " ORDER BY gce.id_code DESC ";
        SQLQuery query = vfData.createSQLQuery(sql);
        vfData.setResultTransformer(query, GenCodeEmployeeBean.class);
        List listResult = query.list();
        if (!CommonUtil.isNullOrEmpty(listResult)) {
            return (GenCodeEmployeeBean) query.list().get(0);
        } else {
            return null;
        }
    }
}
