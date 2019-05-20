package com.korera.service;


import java.util.Calendar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korera.dao.UserDao;
import com.korera.domain.User;
import com.korera.exception.NotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		List<User> users = userDao.findAll();
		return users;
	}

	@Override
	public User getUserByUserName(String userName, String password) {
		// TODO Auto-generated method stub
		User user = userDao.findByUserName(userName);

		if (user != null && password.equals(user.getPassword())) {
			return user;
		}
		
		return null;
	}

	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		User createUser = userDao.findByUserName(user.getUserName());

		if (createUser != null) {

			throw new NotFoundException("User with name " + user.getUserName() + " exists");
	
		}

		createUser = userDao.findByUserEmail(user.getUserEmail());
		if (createUser != null) {
			throw new NotFoundException("User with email " + user.getUserEmail() + " exists");
		}

		// set created date
		user.setCreatedTime( Calendar.getInstance().getTime());
		User createdUser = userDao.save(user);
		
		System.out.println("User created successful!");
		return createdUser;

	}

}
