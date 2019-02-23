package org.ayan.projectmanagement.controller;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ayan.projectmanagement.dto.Projects;
import org.ayan.projectmanagement.dto.Task;
import org.ayan.projectmanagement.service.ProjectsService;
import org.ayan.projectmanagement.service.TaskService;
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
public class TaskController {

	private static Logger logger = LogManager.getLogger(TaskController.class);

	@Autowired
	TaskService taskService;

	@GetMapping("/task/{id}")
	public ResponseEntity<?> getTask(@PathVariable Integer id) {

		try {
			List<Task> task = taskService.getTask(id);
			if (task.isEmpty()) {
				return new ResponseEntity<String>("Task not found", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<List<Task>>(task, HttpStatus.OK);
			}
		} catch (SQLException e) {
			logger.error(e);
			return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);

		}

	}

	@PostMapping("/task")
	public ResponseEntity<String> addTask(@RequestBody(required = true) Task task) {
		try {
			taskService.addTask(task);
		} catch (SQLException e) {
			logger.error(e);
			return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<String>("Added task successfully", HttpStatus.CREATED);
	}

	@DeleteMapping("/task/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable int id) {

		try {
			int rc = taskService.deleteTask(id);
			if (rc == 0) {
				return new ResponseEntity<String>("Task not found to delete", HttpStatus.BAD_REQUEST);
			}

		} catch (SQLException e) {
			logger.error(e);
			return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);

		}

		return new ResponseEntity<String>("Deleted task successfully", HttpStatus.OK);

	}

	@PatchMapping("/task")
	public ResponseEntity<String> updateProjects(@RequestBody(required = true) Task task) {
		try {
			int rc = taskService.updateTask(task);
			if (rc == 0) {
				return new ResponseEntity<String>("Task not found to update", HttpStatus.BAD_REQUEST);
			}
		} catch (SQLException e) {
			logger.error(e);
			return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<String>("Updated Task successfully", HttpStatus.OK);
	}
}
