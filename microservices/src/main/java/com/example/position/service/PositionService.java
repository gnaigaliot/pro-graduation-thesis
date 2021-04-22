package com.example.position.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.position.dao.PositionDAO;
import com.example.position.entity.PositionBO;

@Service
public class PositionService {
	@Autowired
	private PositionDAO positionDAO;
	
	public void saveOrUpdate(PositionBO bo) {
		positionDAO.save(bo);
    }
    
    public void saveOrUpdateAll(List<PositionBO	> lstBo) {
    	positionDAO.saveAll(lstBo);
    }
}
