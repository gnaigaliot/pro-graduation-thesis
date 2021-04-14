package com.example.user.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.user.dao.RoleDAO;
import com.example.user.entity.RoleBO;



/**
 * @author TanPTN
 * @since May 3, 2019
 * @version 1.0
 */
@Service
public class RoleService {
    @Autowired
    private RoleDAO roleDAO;
    
    @Autowired
    private EntityManager entityManager;
    
    public List<RoleBO> findAll() {
        return roleDAO.findAll();
    }
//    public List<String> findUserAccount(Long userId) {
//        return roleDAO.getRole(userId, entityManager);
//    }
    
    

    
}
