package com.example.position.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.position.entity.PositionBO;

@Transactional
@Repository
public interface PositionDAO extends CrudRepository<PositionBO, Long> {

}
