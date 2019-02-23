package org.ayan.projectmanagement.service;

import java.sql.SQLException;
import java.util.List;

import org.ayan.projectmanagement.dao.TaskDAO;
import org.ayan.projectmanagement.dto.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskService {
	@Autowired
	TaskDAO taskDAO;

	public List<Task> getTask(Integer taskid) throws SQLException {
		return taskDAO.getTask(taskid);
	}

	public void addTask(Task task) throws SQLException {
		taskDAO.addTask(task);
	}

	public int deleteTask(Integer taskid) throws SQLException {
		return taskDAO.deleteTask(taskid);

	}

	public int updateTask(Task task) throws SQLException {
		return taskDAO.updateTask(task);

	}
}
