package org.ayan.projectmanagement.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ProjectsRowMapper implements RowMapper<Projects> {

	@Override
	public Projects mapRow(ResultSet rs, int rowNum) throws SQLException {
		Projects projects = new Projects();
		projects.setProjectid(rs.getInt("PROJECT_ID"));
		projects.setDescription(rs.getString("PROJECT_DESC"));
		projects.setStartDate(rs.getString("START_DATE"));
		projects.setEndDate(rs.getString("END_DATE"));
		projects.setPriority(rs.getInt("PRIORTY"));

		return projects;
	}

}
