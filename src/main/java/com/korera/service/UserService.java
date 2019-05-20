package com.korera.service;

import java.util.List;

import com.korera.domain.User;

public interface UserService {
	
	public List<User> getAll();
	
	public User getUserByUserName(String userName, String password);
	
	public User createUser(User user);
	
	

}
