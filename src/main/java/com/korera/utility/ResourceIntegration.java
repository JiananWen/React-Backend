package com.korera.utility;

import java.util.HashMap;
import java.util.Map;

import com.korera.domain.Data;
import com.korera.domain.Resource;

public class ResourceIntegration {

	private Resource resource;
	private Map<String, Data> titleDataMap;

	public ResourceIntegration() {
		titleDataMap = new HashMap<>();
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Map<String, Data> getTitleDataMap() {
		return titleDataMap;
	}

	public void setTitleDataMap(Map<String, Data> titleDataMap) {
		this.titleDataMap = titleDataMap;
	}

}
