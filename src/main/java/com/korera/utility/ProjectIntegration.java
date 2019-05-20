package com.korera.utility;

import java.util.List;
import java.util.Map;


public class ProjectIntegration {
	
	private Long projectId;
	private String projectName;
	private List<ResourceIntegration> ris;
	private Map<String, String> ttMap ;
	
	public ProjectIntegration() {
		
	}
	
	public ProjectIntegration(Long projectId, String projectName, List<ResourceIntegration> ris, Map<String, String> ttMap) {
		this.projectId = projectId;
		this.projectName = projectName;
		this.ris = ris;
		this.ttMap = ttMap;
	}



	

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<ResourceIntegration> getRis() {
		return ris;
	}

	public void setRis(List<ResourceIntegration> ris) {
		this.ris = ris;
	}

	public Map<String, String> getTtMap() {
		return ttMap;
	}

	public void setTtMap(Map<String, String> ttMap) {
		this.ttMap = ttMap;
	}
	
	

	
}
