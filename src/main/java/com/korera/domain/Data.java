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
@Table(name = "Data")
public class Data {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dataId;

	@Column
	private String dataValue;

	@Column
	private Long resourceId;

	@Column
	private Long titleId;

	@Column
	private Long projectId;

	@ManyToOne
	@JoinColumn(name = "projectId", insertable = false, updatable = false)
	private Project project;

	public Data() {

	}

	public Long getDataId() {
		return dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public Long getTitleId() {
		return titleId;
	}

	public void setTitleId(Long titleId) {
		this.titleId = titleId;
	}
	
	

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@Override
	public String toString() {
		return "Data [dataValue=" + dataValue + ", resourceId=" + resourceId + ", titleId=" + titleId + "]";
	}

}
