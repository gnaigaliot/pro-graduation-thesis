package com.example.user.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.user.entity.RoleBO;
import com.example.user.entity.UserBO;


/**
 * @author TanPTN
 * @since May 3, 2019
 * @version 1.0	
 */
@Transactional
@Repository
public interface RoleDAO extends CrudRepository<RoleBO, Long> {

//    public List<String> getRole(Long userId, EntityManager entityManager) {
//        String sql = "SELECT rl.role FROM " + RoleBO.class.getName() + " rl "
//                + " INNER JOIN " + UserRoleBO.class.getName() + " url ON rl.roleId = url.roleId"
//                + " WHERE url.userId = :userId ";
// 
//        Query query = entityManager.createQuery(sql, String.class);
//        query.setParameter("userId", userId);
//        return query.getResultList();
//    }
    

    public List<RoleBO> findAll();
    
    //Lấy ra tat ca role bởi userCode
    @Query("SELECT r.role FROM RoleBO r "
    		+ " Inner join UserRoleBO ur on r.roleId = ur.roleId"
    		+ " Inner join UserBO u on  ur.userId = u.userId "
    		+ " WHERE LOWER(u.userCode) = LOWER(:userCode)")
    public List<String> findAllRoleOfUserCode(@Param("userCode")String userCode);

}
