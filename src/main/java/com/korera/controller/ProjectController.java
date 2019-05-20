package com.korera.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.korera.domain.Project;
import com.korera.service.ProjectService;
import com.korera.utility.ProjectIntegration;
import com.korera.utility.ResourceIntegration;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	
	// get all projects
	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	public List<Project> getAllProjects() {
		
		List<Project> projects = projectService.getAllProjects();
		return projects;
	}
	
	// get the first project, display in the resource page
	@RequestMapping(value="/project", method = RequestMethod.GET)
	public ProjectIntegration getDefaultProject() {
		ProjectIntegration project = projectService.getDefaultProject();
		return project;
	}
	
	// get specific project by id, used to choose different project
	@RequestMapping(value = "/projects/{id}", method = RequestMethod.GET)
	public ProjectIntegration getProject(@PathVariable (value = "id") Long projectId) {
		ProjectIntegration project = projectService.getProjectById(projectId);
		return project;
	}
	
	@RequestMapping(value = "projects/saveupdate", method = RequestMethod.POST)
	public ProjectIntegration saveProject(@RequestBody ProjectIntegration project) {
		

		ProjectIntegration projectIntegration = projectService.saveOrUpdateProject(project);
		
		return projectIntegration;
		
	}
	
	@RequestMapping(value = "projects/addrow", method = RequestMethod.POST)
	public ProjectIntegration addRow(@RequestBody ResourceIntegration ri) {
		
		ProjectIntegration projectIntegration = projectService.addRow(ri);
		
		return projectIntegration;

		
	}
	
	
	@RequestMapping(value="projects/update", method = RequestMethod.POST)
	public ProjectIntegration updateProject(@RequestBody ProjectIntegration project) {
		ProjectIntegration projectIntegration = projectService.updateProject(project);
		return projectIntegration;
	}
	


}
