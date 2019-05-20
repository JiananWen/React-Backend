package com.korera.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Titles")
public class Title {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long titleId;
	
	@Column
	private String titleName;
	
	@Column
	private String titleType;
	
	@Column
	private Long projectId;
	
	@ManyToOne
	@JoinColumn(name = "projectId", insertable=false, updatable=false)
	private Project project;
	
	public Title() {
		
	}

	public Long getTitleId() {
		return titleId;
	}

	public void setTitleId(Long titleId) {
		this.titleId = titleId;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	
	

	public String getTitleType() {
		return titleType;
	}

	public void setTitleType(String titleType) {
		this.titleType = titleType;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@Override
	public String toString() {
		return "Title [titleId=" + titleId + ", titleName=" + titleName + ", titleType=" + titleType + "]";
	}

	
	
	
	
	
	
	
}
