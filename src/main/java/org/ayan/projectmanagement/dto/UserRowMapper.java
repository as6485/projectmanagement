package org.ayan.projectmanagement.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {
	

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUserid(rs.getInt("USER_ID"));
		user.setFirstname(rs.getString("FIRST_NAME"));
		user.setLastname(rs.getString("LAST_NAME"));
		user.setEmpid(rs.getInt("EMP_ID"));
		user.setProjectid(rs.getInt("PROJECT_ID"));
		user.setTaskid(rs.getInt("TASK_ID"));

		return user;
	}
}
