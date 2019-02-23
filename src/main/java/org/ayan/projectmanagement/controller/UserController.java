package org.ayan.projectmanagement.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ayan.projectmanagement.dto.User;
import org.ayan.projectmanagement.service.UserService;
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
public class UserController {
	private static Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUser(@PathVariable Integer id) {

		try {
			List<User> user = userService.getUser(id);
			if (user.isEmpty()) {
				return new ResponseEntity<String>("User not found", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<List<User>>(user, HttpStatus.OK);
			}
		} catch (SQLException e) {
			logger.error(e);
			return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers() {

		try {
			List<User> users = userService.getAllUsers();
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		} catch (SQLException e) {
			logger.error(e);
			return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);

		}
	}
	
	@GetMapping("/usersbyid")
	public ResponseEntity<?> getAllUsersSortEmpById() {

		try {
			List<User> users = userService.getAllUsersSortEmpById();
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		} catch (SQLException e) {
			logger.error(e);
			return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);

		}
	}
	
	@GetMapping("/usersbyfname")
	public ResponseEntity<?> getAllUsersSortFname() {

		try {
			List<User> users = userService.getAllUsersSortFname();
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		} catch (SQLException e) {
			logger.error(e);
			return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);

		}
	}
	
	@GetMapping("/usersbylname")
	public ResponseEntity<?> getAllUsersSortLname() {

		try {
			List<User> users = userService.getAllUsersSortLname();
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		} catch (SQLException e) {
			logger.error(e);
			return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);

		}
	}

	@PostMapping("/user")
	public ResponseEntity<?> addUser(@RequestBody(required = true) User user) {
		try {
			userService.addUser(user);
		} catch (SQLException e) {
			logger.error(e);
			return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<Map> deleteUser(@PathVariable int id) {

		Map<Integer, String> respMap = new HashMap<Integer, String>();

		try {
			int rc = userService.deleteUser(id);
			if (rc == 0) {
				respMap.put(404, "User not found to delete");
				return new ResponseEntity<Map>(respMap, HttpStatus.BAD_REQUEST);
			}

		} catch (SQLException e) {
			logger.error(e);
			respMap.put(400, e.toString());
			return new ResponseEntity<Map>(respMap, HttpStatus.BAD_REQUEST);

		}
		respMap.put(200, "Deleted user successfully");
		return new ResponseEntity<Map>(respMap, HttpStatus.OK);

	}

	@PatchMapping("/user")
	public ResponseEntity<?> updateUser(@RequestBody(required = true) User user) {
		try {
			int rc = userService.updateUser(user);
			if (rc == 0) {
				return new ResponseEntity<String>("User not found to update", HttpStatus.BAD_REQUEST);
			}
		} catch (SQLException e) {
			logger.error(e);
			return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
