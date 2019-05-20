package com.korera.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korera.domain.Project;

@Repository
public interface ProjectDao extends JpaRepository<Project, Long>{

	Project findByProjectName(String projectName);
	
	Project findByProjectId(Long projectId);
	
}
