package org.ayan.projectmanagement.controller;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ayan.projectmanagement.dao.ParentTaskDAO;
import org.ayan.projectmanagement.dto.ParentTask;
import org.ayan.projectmanagement.service.ParentTaskService;
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
public class ParentTaskController {
	private static Logger logger = LogManager.getLogger(ParentTaskController.class);

	@Autowired
	ParentTaskService parentTaskService;

	@GetMapping("/parent/{id}")
	public ResponseEntity<?> getParentTask(@PathVariable Integer id) {

		try {
			List<ParentTask> parentTask = parentTaskService.getParentTask(id);
			if (parentTask.isEmpty()) {
				return new ResponseEntity<String>("Parent task not found", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<List<ParentTask>>(parentTask, HttpStatus.OK);
			}
		} catch (SQLException e) {
			logger.error(e);
			return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);

		}

	}

	@PostMapping("/parent")
	public ResponseEntity<String> addParentTask(@RequestBody(required = true) ParentTask parentTask) {
		try {
			parentTaskService.addParentTask(parentTask);
		} catch (SQLException e) {
			logger.error(e);
			return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<String>("Added parent task successfully", HttpStatus.CREATED);
	}

	@DeleteMapping("/parent/{parentid}")
	public ResponseEntity<?> deleteParentTask(@PathVariable int parentid) {

		try {
			int rc = parentTaskService.deleteParentTask(parentid);
			if (rc == 0) {
				return new ResponseEntity<String>("Parent task not found to delete", HttpStatus.BAD_REQUEST);
			}

		} catch (SQLException e) {
			logger.error(e);
			return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);

		}

		return new ResponseEntity<String>("Deleted parent task successfully", HttpStatus.OK);

	}

	@PatchMapping("/parent")
	public ResponseEntity<String> updateBook(@RequestBody(required = true) ParentTask parentTask) {
		try {
			int rc = parentTaskService.updateParentTask(parentTask);
			if (rc == 0) {
				return new ResponseEntity<String>("Parent Task not found to update", HttpStatus.BAD_REQUEST);
			}
		} catch (SQLException e) {
			logger.error(e);
			return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<String>("Updated Parent Task successfully", HttpStatus.OK);
	}

}
