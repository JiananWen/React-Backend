package com.korera.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.korera.domain.Resource;
import com.korera.service.ResourceService;

@RestController
public class ResourceController {
	
	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping(value = "/resources", method = RequestMethod.GET)
	public List<Resource> getAllResources() {
		return resourceService.getAllResources();
	}
	
	

}
