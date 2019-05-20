package com.korera.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korera.dao.DataDao;
import com.korera.dao.ProjectDao;
import com.korera.dao.ResourceDao;
import com.korera.dao.TitleDao;
import com.korera.domain.Data;
import com.korera.domain.Project;
import com.korera.domain.Resource;
import com.korera.domain.Title;
import com.korera.utility.ProjectIntegration;
import com.korera.utility.ResourceIntegration;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private TitleDao titleDao;

	@Autowired
	private ResourceDao resourceDao;

	@Autowired
	private DataDao dataDao;

	@Override
	public ProjectIntegration getProjectById(Long projectId) {
		// TODO Auto-generated method stub
		Project project = projectDao.findByProjectId(projectId);

		// convert project to standard table format with project name

		List<ResourceIntegration> ris = resourceToIntegration(project);
		Map<String, String> map = titleToMap(project);
		ProjectIntegration pi = new ProjectIntegration(project.getProjectId(), project.getProjectName(), ris, map);
		return pi;
	}

	@Override
	public List<Project> getAllProjects() {
		// TODO Auto-generated method stub
		List<Project> projects = projectDao.findAll();

		return projects;

	}

	@Override
	public ProjectIntegration getDefaultProject() {
		// TODO Auto-generated method stub
		List<Project> projects = projectDao.findAll();
		Project project = projects.get(0);

		List<ResourceIntegration> ris = resourceToIntegration(project);
		Map<String, String> map = titleToMap(project);
		ProjectIntegration pi = new ProjectIntegration(project.getProjectId(), project.getProjectName(), ris, map);
		return pi;

	}

	private Map<String, String> titleToMap(Project project){
		Set<Title> titles = project.getTitles();
		Map<String, String> map = new HashMap<>();
		
		for(Title title : titles) {
			map.put(title.getTitleName(), title.getTitleType());
		}
		
		return map;
	}
	// convert resources, titles, datas to one special resource type
	private List<ResourceIntegration> resourceToIntegration(Project project) {

		// get all resource
		Set<Resource> resources = project.getResources();
		Set<Title> titles = project.getTitles();
		System.out.println(titles);
		Set<Data> datas = project.getDatas();

		List<ResourceIntegration> result = new ArrayList<>();

		for (Resource resource : resources) {
			ResourceIntegration ri = new ResourceIntegration();

			ri.setResource(resource);

			Map<Title, Data> map = new HashMap<>();

			// set title
			for (Title title : titles) {
				map.put(title, null);

			}

			// set value for specific title

			for (Title title : map.keySet()) {

				for (Data data : datas) {

					if (data.getResourceId() == resource.getResourceId() && data.getTitleId() == title.getTitleId()) {

						map.put(title, data);
					}
				}
			}

			Map<String, Data> map2 = ri.getTitleDataMap();

			for (Title title : map.keySet()) {
				map2.put(title.getTitleName(), map.get(title));
			}

			ri.setTitleDataMap(map2);
			result.add(ri);

		}

		return result;

	}

	@Override
	public ProjectIntegration saveOrUpdateProject(ProjectIntegration project) {
		// TODO Auto-generated method stub
		Long projectId = project.getProjectId();
		List<ResourceIntegration> ris = project.getRis();

		Map<String, Long> titleAndId = new HashMap<>();

		// save title, save titleID in titleAndId
		for (ResourceIntegration ri : ris) {
			Map<String, Data> map = ri.getTitleDataMap();

			for (String titleName : map.keySet()) {

				// get titleId
				Long titleId = map.get(titleName).getTitleId();

				// save new title into database, if titleId != null -> it will update the record
				if (titleId == null) {
					Title newTitle = new Title();

					newTitle.setTitleName(titleName);
					newTitle.setProjectId(projectId);
					Title reTitle = titleDao.save(newTitle);
					titleAndId.put(titleName, reTitle.getTitleId());
				} else {
					titleAndId.put(titleName, titleId);
				}

			}
			System.out.println(titleAndId);

			// only save title once in every save process
			break;
		}

		// save resources or update resources and save reousrceId in
		Map<Resource, Long> resourceAndIdMap = new HashMap<>();

		for (ResourceIntegration ri : ris) {
			Resource resource = ri.getResource();

			resource.setProjectId(projectId);

			// if resourceId == null, means add a new resource record
			if (resource.getResourceId() == null) {
				Resource returnResource = resourceDao.save(resource);
				resourceAndIdMap.put(resource, returnResource.getResourceId());
			} else {

				resourceAndIdMap.put(resource, resource.getResourceId());
			}

		}
//
//		System.out.println("resource saved");
//
//		// save datas
//
		for (ResourceIntegration ri : ris) {
			Map<String, Data> map = ri.getTitleDataMap();

			for (String title : map.keySet()) {
				Long titleId = titleAndId.get(title);
				Long resourceId = resourceAndIdMap.get(ri.getResource());

				Data data = map.get(title);

				data.setResourceId(resourceId);
				data.setTitleId(titleId);
				data.setProjectId(projectId);

				dataDao.save(data);

			}

		}

		// return project

		return getProjectById(projectId);
	}

	@Override
	public ProjectIntegration updateProject(ProjectIntegration project) {
		// TODO Auto-generated method stub
		Long projectId = project.getProjectId();
		List<ResourceIntegration> ris = project.getRis();

		// update resources

		for (ResourceIntegration ri : ris) {
			Resource resource = ri.getResource();

			resource.setProjectId(projectId);
			// because it's update, it must have resourceID
			resourceDao.save(resource);

		}

		System.out.println("resource saved");

		// update Data

		/*
		 * request data: "Price": { "dataValue": "newData", "resourceId": 121,
		 * "titleId": 102, "projectId": 10000 }
		 * 
		 * resourceId, titleId, projectId all have without dataId ->
		 * 
		 * 1. first check dateId exist or not 2. if has, update 3. if doesn't have ->
		 * create new data
		 */
		for (ResourceIntegration ri : ris) {
			Map<String, Data> map = ri.getTitleDataMap();

			for (String title : map.keySet()) {

				Data data = map.get(title);

				List<Data> checkDatas = dataDao.findByProjectIdAndResourceIdAndTitleId(projectId, data.getResourceId(),
						data.getTitleId());

				if (checkDatas.size() > 0) {
					Long dataId = checkDatas.get(0).getDataId();

					System.out.println("update data: " + dataId);
					data.setDataId(dataId);
				}

				dataDao.save(data);

			}
		}

		System.out.println("data saved");

		return getProjectById(projectId);

	}

	@Override
	public ProjectIntegration addRow(ResourceIntegration ri) {
		// TODO Auto-generated method stub

		// add resource
		Resource newResource = new Resource();
		newResource.setProjectId(ri.getResource().getProjectId());
		newResource.setResourceName(ri.getResource().getResourceName());
		newResource.setResourceCode(ri.getResource().getResourceCode());

		Resource returnResource = resourceDao.save(newResource);

		// add data
		Map<String, Data> map = ri.getTitleDataMap();
		for (Data data : map.values()) {
			data.setResourceId(returnResource.getResourceId());
			dataDao.save(data);
		}

		return getProjectById(ri.getResource().getProjectId());
	}

}
