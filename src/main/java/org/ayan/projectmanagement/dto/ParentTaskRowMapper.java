package org.ayan.projectmanagement.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ParentTaskRowMapper implements RowMapper<ParentTask> {

	@Override
	public ParentTask mapRow(ResultSet rs, int rowNum) throws SQLException {
		ParentTask parentTask = new ParentTask();
		parentTask.setId(rs.getInt("PARENT_ID"));
		parentTask.setDescription(rs.getString("PARENT_TASK_DESC"));

		return parentTask;
	}

}
