package com.activeledger.health.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.activeledger.health.model.User;

@Repository("userDao")
public interface UserDao  extends JpaRepository<User,Long> {
	
	User findByUsername(String username);

}
