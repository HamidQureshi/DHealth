/**
 * 
 *//*
package com.activeledger.health.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activeledger.health.dao.UserDao;
import com.activeledger.health.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
	//	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userDao.save(user);
	}

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub

		User user= userDao.findByEmail(email);
		
		return user;
	}



	

}
*/