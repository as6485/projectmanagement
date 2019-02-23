package org.ayan.projectmanagement.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TaskRowMapper implements RowMapper<Task> {

	@Override
	public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
		Task task = new Task();
		task.setTaskid(rs.getInt("TASK_ID"));
		task.setParentid(rs.getInt("PARENT_ID"));
		task.setProjectid(rs.getInt("PROJECT_ID"));
		task.setDescription(rs.getString("TASK_DESC"));
		task.setStartDate(rs.getString("START_DATE"));
		task.setEndDate(rs.getString("END_DATE"));
		task.setPriority(rs.getInt("PRIORTY"));
		task.setStatus(rs.getBoolean("TASK_STATUS"));

		return task;
	}

}
