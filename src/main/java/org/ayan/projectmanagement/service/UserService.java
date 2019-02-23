package org.ayan.projectmanagement.service;

import java.sql.SQLException;
import java.util.List;

import org.ayan.projectmanagement.dao.UserDAO;
import org.ayan.projectmanagement.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

	@Autowired
	UserDAO userDAO;

	public List<User> getUser(Integer userid) throws SQLException {
		return userDAO.getUser(userid);
	}

	public List<User> getAllUsers() throws SQLException {
		return userDAO.getAllUsers();
	}
	
	public List<User> getAllUsersSortEmpById() throws SQLException {
		return userDAO.getAllUsersSortEmpById();
	}
	
	public List<User> getAllUsersSortFname() throws SQLException {
		return userDAO.getAllUsersSortFname();
	}
	
	public List<User> getAllUsersSortLname() throws SQLException {
		return userDAO.getAllUsersSortLname();
	}
	
	public void addUser(User user) throws SQLException {
		userDAO.addUser(user);
	}

	public int deleteUser(Integer userid) throws SQLException {
		return userDAO.deleteUser(userid);

	}

	
	public int updateUser(User user) throws SQLException {
		return userDAO.updateUser(user);

	}
}
