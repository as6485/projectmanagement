package org.ayan.projectmanagement.service;

import java.sql.SQLException;
import java.util.List;

import org.ayan.projectmanagement.dao.ProjectsDAO;
import org.ayan.projectmanagement.dto.Projects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectsService {
	@Autowired
	ProjectsDAO projectsDAO;

	public List<Projects> getProjects(Integer projectid) throws SQLException {
		return projectsDAO.getProjects(projectid);
	}

	public void addProjects(Projects projects) throws SQLException {
		projectsDAO.addProjects(projects);
	}

	public int deleteProjects(Integer projectid) throws SQLException {
		return projectsDAO.deleteProjects(projectid);

	}

	public int updateProjects(Projects projects) throws SQLException {
		return projectsDAO.updateProjects(projects);

	}
}
