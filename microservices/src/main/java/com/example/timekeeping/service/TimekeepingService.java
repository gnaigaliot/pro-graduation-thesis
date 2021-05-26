package com.example.timekeeping.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.common.DataTableResults;
import com.example.common.VfData;
import com.example.report.LineChartBean;
import com.example.timekeeping.bean.TimekeepingBean;
import com.example.timekeeping.bo.TimekeepingBO;
import com.example.timekeeping.dao.TimekeepingDAO;
import com.example.timekeeping.form.TimekeepingForm;

@Service
public class TimekeepingService {

    @Autowired
    private TimekeepingDAO timekeepingdao;
    @Autowired
    private VfData vfData;

    /**
     * findById
     * @param EmployeeId
     * @return
     */
    public TimekeepingBO findById(Long timkeepingId) {
        return timekeepingdao.findById(timkeepingId).orElse(null);
    }

    /**
     * getDatatables
     * @param employeeForm
     * @return
     */
    public DataTableResults<TimekeepingBean> getDatatables(TimekeepingForm timekeepingForm) {
        DataTableResults<TimekeepingBean> listData = timekeepingdao.getDatatables(vfData, timekeepingForm);
        return listData;
    }

    /**
     * saveOrUpdate
     * @param entity
     */
    @Transactional
    public void saveOrUpdate(TimekeepingBO entity) {
        timekeepingdao.save(entity);
    }

    /**
     * delete
     * @param entity
     */
    public void delete(TimekeepingBO entity) {
        timekeepingdao.delete(entity);
    }

    public List<LineChartBean> getLineChartData(Date firstDate, Date lastDate) {
        return timekeepingdao.getListDataLineChart(vfData, firstDate, lastDate);
    }
}
