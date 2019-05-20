package com.korera.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korera.domain.User;

@Repository
public interface UserDao extends JpaRepository<User, Long>{
	
	User findByUserName(String userName);
	
	User findByUserEmail(String userEmail);
	
	
}
