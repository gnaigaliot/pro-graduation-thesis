package com.example.timekeeping.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.common.CommonUtil;
import com.example.common.DataTableResults;
import com.example.common.VfData;
import com.example.report.LineChartBean;
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
    
    public default List<LineChartBean> getListDataLineChart(VfData vfData, Date monday, Date sunday) {
        String sql = " SELECT "
                + "    v.selected_date As dateInWeek, "
                + "    IF(a.totalLate > 0, a.totalLate, 0) As arrivalLate, "
                + "    IF(a.early > 0, a.early, 0) As departureEarly, "
                + "    IF(a.total > 0, a.total, 0) As totalEmp "
                + "  FROM v_fullday v "
                + "  LEFT JOIN ( "
                + "  SELECT "
                + "    t.date_timekeeping, "
                + "    SUM(t.is_late) As totalLate, "
                + "    SUM(t.left_early) As early, "
                + "    SUM(IF(t.timekeeping_id > 0, 1, 0)) As total "
                + "  FROM timekeeping t "
                + "  GROUP BY t.date_timekeeping ) a ON v.selected_date = a.date_timekeeping "
                + "  WHERE DATE(v.selected_date) BETWEEN STR_TO_DATE(:firstDate, '%d/%m/%Y') "
                + "  AND STR_TO_DATE(:lastDate, '%d/%m/%Y') "
                + "  ORDER BY v.selected_date ASC ";
        SQLQuery query = vfData.createSQLQuery(sql);
        query.setParameter("firstDate", CommonUtil.convertDateToString(monday));
        query.setParameter("lastDate", CommonUtil.convertDateToString(sunday));
        vfData.setResultTransformer(query, LineChartBean.class);
        return query.list();
    }
}
