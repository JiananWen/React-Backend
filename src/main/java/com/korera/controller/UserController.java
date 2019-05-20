package com.korera.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.korera.domain.User;
import com.korera.exception.UnauthorizedException;
import com.korera.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {
	
	@Autowired
	UserService userService;

	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> getAllUsers(){
		
		List<User> users = userService.getAll();
		
		return users;
	}
	
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public User login(@RequestBody User inputUser, HttpServletResponse response) throws Exception {
		
		String userName = inputUser.getUserName();
		String password = inputUser.getPassword();
		
		System.out.println(userName);
		System.out.println(password);
		
		System.out.println();
		
		
		User user = userService.getUserByUserName(userName, password);
		
		if(user == null) {
			throw new UnauthorizedException("user doesn't exist");
		}
		
		// set jwt
//		Object userWithJwt = (Object) user;
		
		return user;
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public User register(@RequestBody User inputUser) {
		User createdUser = userService.createUser(inputUser);
		
		return createdUser;
	}
}
