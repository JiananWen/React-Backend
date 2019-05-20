package com.korera.service;

import java.util.List;

import com.korera.domain.Project;
import com.korera.utility.ProjectIntegration;
import com.korera.utility.ResourceIntegration;

public interface ProjectService {
	
	public ProjectIntegration getProjectById(Long projectId);
	
	public List<Project> getAllProjects();
	
	public ProjectIntegration getDefaultProject();
	
	public ProjectIntegration saveOrUpdateProject(ProjectIntegration project);
	
	public ProjectIntegration updateProject(ProjectIntegration project);
	
	public ProjectIntegration addRow(ResourceIntegration ri);

}
