package com.example.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.common.CommonUtil;
import com.example.user.dao.UserRoleDAO;
import com.example.user.entity.UserRoleBO;



/**
 * @author TanPTN
 * @since May 3, 2019
 * @version 1.0
 */
@Service
public class UserRoleService {
    @Autowired
    private UserRoleDAO userRoleDAO;
    
    public void saveOrUpdate(UserRoleBO bo) {
    	userRoleDAO.save(bo);
    }
    
    public void saveOrUpdateAll(List<UserRoleBO> lstBo) {
    	userRoleDAO.saveAll(lstBo);
    }
    
    public void deleteAllByUserId(Long userId) {
    	userRoleDAO.deleteByUserId(userId);
    }

	public List<Long> findRoleByUserId(Long id) {
		List<UserRoleBO> lstBO = userRoleDAO.findByUserId(id);
		List<Long> lstRoleId = new ArrayList<>();
		if(!CommonUtil.isNullOrEmpty(lstBO)) {
			for (UserRoleBO long1 : lstBO) {
				lstRoleId.add(long1.getRoleId());
			}
		}
		return lstRoleId;
	}

}
