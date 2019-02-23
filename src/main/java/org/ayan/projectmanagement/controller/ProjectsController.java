package org.ayan.projectmanagement.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ayan.projectmanagement.dto.Projects;
import org.ayan.projectmanagement.service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class ProjectsController {
	private static Logger logger = LogManager.getLogger(ProjectsController.class);

	@Autowired
	ProjectsService projectsService;

	@GetMapping("/projects/{id}")
	public ResponseEntity<?> getProjects(@PathVariable Integer id) {

		try {
			List<Projects> projects = projectsService.getProjects(id);
			if (projects.isEmpty()) {
				return new ResponseEntity<String>("Project not found", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<List<Projects>>(projects, HttpStatus.OK);
			}
		} catch (SQLException e) {
			logger.error(e);
			return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);

		}

	}

	@PostMapping("/projects")
	public ResponseEntity<String> addProjects(@RequestBody(required = true) Projects projects) {
		try {
			projectsService.addProjects(projects);
		} catch (SQLException e) {
			logger.error(e);
			return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<String>("Added project successfully", HttpStatus.CREATED);
	}

	@DeleteMapping("/projects/{id}")
	public ResponseEntity<?> deleteProjects(@PathVariable int id) {

		try {
			int rc = projectsService.deleteProjects(id);
			if (rc == 0) {
				return new ResponseEntity<String>("Project not found to delete", HttpStatus.BAD_REQUEST);
			}

		} catch (SQLException e) {
			logger.error(e);
			return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);

		}

		return new ResponseEntity<String>("Deleted project successfully", HttpStatus.OK);

	}

	@PatchMapping("/projects")
	public ResponseEntity<String> updateProjects(@RequestBody(required = true) Projects projects) {
		try {
			int rc = projectsService.updateProjects(projects);
			if (rc == 0) {
				return new ResponseEntity<String>("Project not found to update", HttpStatus.BAD_REQUEST);
			}
		} catch (SQLException e) {
			logger.error(e);
			return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<String>("Updated Project successfully", HttpStatus.OK);
	}
}
