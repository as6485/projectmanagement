package org.ayan.projectmanagement.service;

import java.sql.SQLException;
import java.util.List;

import org.ayan.projectmanagement.dao.ParentTaskDAO;
import org.ayan.projectmanagement.dto.ParentTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ParentTaskService {
	@Autowired
	ParentTaskDAO parentTaskDAO;

	public List<ParentTask> getParentTask(Integer parentid) throws SQLException {
		return parentTaskDAO.getParentTask(parentid);
	}

	public void addParentTask(ParentTask parentTask) throws SQLException {
		parentTaskDAO.addParentTask(parentTask);
	}

	public int deleteParentTask(Integer parentid) throws SQLException {
		return parentTaskDAO.deleteParentTask(parentid);

	}

	public int updateParentTask(ParentTask parentTask) throws SQLException {
		return parentTaskDAO.updateParentTask(parentTask);

	}
}
