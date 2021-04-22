package com.example.user.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.user.entity.RoleBO;

@Transactional
@Repository
public interface RoleDAO extends CrudRepository<RoleBO, Long> {

    public List<RoleBO> findAll();
    
    //Lấy ra tat ca role bởi userCode
    @Query("SELECT r.role FROM RoleBO r "
            + " Inner join UserRoleBO ur on r.roleId = ur.roleId"
            + " Inner join UserBO u on  ur.userId = u.userId "
            + " WHERE LOWER(u.userName) = LOWER(:userName)")
    public List<String> findAllRoleOfUserCode(@Param("userName")String userName);

}
