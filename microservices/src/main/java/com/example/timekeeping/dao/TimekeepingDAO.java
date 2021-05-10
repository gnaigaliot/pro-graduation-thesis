package com.example.timekeeping.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.common.CommonUtil;
import com.example.common.DataTableResults;
import com.example.common.VfData;
import com.example.timekeeping.bean.TimekeepingBean;
import com.example.timekeeping.bo.TimekeepingBO;
import com.example.timekeeping.form.TimekeepingForm;

@Transactional
@Repository
public interface TimekeepingDAO extends CrudRepository<TimekeepingBO, Long> {

    public List<TimekeepingBO> findAll();

    /**
     * get data by datatable
     * @param vfData
     * @param TimekeepingForm
     * @return
     */
    public default DataTableResults<TimekeepingBean> getDatatables(VfData vfData, TimekeepingForm formData) {
        List<Object> paramList = new ArrayList<>();

        String sql = " SELECT ";
        sql += "        t.timekeeping_id As timekeepingId   ";
        sql += "       ,t.employee_id As employeeId ";
        sql += "       ,t.is_late As isLate ";
        sql += "       ,t.left_early As leftEarly  ";
        sql += "       ,t.date_timekeeping As dateTimekeeping       ";
        sql += "       ,t.departure_time As departureTime        ";
        sql += "       ,t.departure_picture As departurePicture  ";
        sql += "       ,t.arrival_time As arrivalTime       ";
        sql += "       ,t.arrival_picture As arrivalPicture  ";
        sql += "       ,t.arrival_late_time As arrivalLateTime ";
        sql += "       ,t.departure_early_time As departureEarlyTime    ";
        sql += "       ,e.employee_name As employeeName  ";
        sql += "       FROM timekeeping t ";
        sql += "       INNER JOIN employee e ON t.employee_id = e.employee_id AND e.status = 1 ";

        StringBuilder strCondition = new StringBuilder(" WHERE 1 = 1 ");
        
        CommonUtil.filter(formData.getDateTimekeeping(), strCondition, paramList, "t.date_timekeeping");
        CommonUtil.filter(formData.getEmployeeName(), strCondition, paramList, "e.employee_name");
        
        String orderBy = " ORDER BY timekeepingId DESC";
        return vfData.findPaginationQuery(sql + strCondition.toString(), orderBy, paramList, TimekeepingBean.class);
    }
}
