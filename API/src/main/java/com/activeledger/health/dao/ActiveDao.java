package com.activeledger.health.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.activeledger.health.model.User;


@Transactional
@Repository
public class ActiveDao {
	
	
	@PersistenceContext
    private EntityManager entityManager;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	
	public void saveUser(User user)
	{
		entityManager.persist(user);
	}

}
