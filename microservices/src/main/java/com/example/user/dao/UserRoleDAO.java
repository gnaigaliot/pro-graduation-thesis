package com.example.user.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.user.entity.UserRoleBO;

@Transactional
@Repository
public interface UserRoleDAO extends CrudRepository<UserRoleBO, Long> {

    public List<UserRoleBO> findByUserId(Long userId);
    public void deleteByUserId(Long userId);

}
