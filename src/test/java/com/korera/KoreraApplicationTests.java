package com.korera;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.korera.dao.ProjectDao;
import com.korera.dao.UserDao;


@RunWith(SpringRunner.class)
@SpringBootTest
public class KoreraApplicationTests {
	
	@Autowired
	ProjectDao projectDao;

	@Test
	public void contextLoads() {
		System.out.println("in test");
	}
	

}
