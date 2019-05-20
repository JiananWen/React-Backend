package com.korera.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korera.dao.ResourceDao;
import com.korera.domain.Resource;

@Service
public class ResourceServiceImpl implements ResourceService{
	
	@Autowired
	private ResourceDao resourceDao;

	@Override
	public List<Resource> getAllResources() {
		// TODO Auto-generated method stub
		return resourceDao.findAll();
	}

}
