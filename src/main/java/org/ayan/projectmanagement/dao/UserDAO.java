package org.ayan.projectmanagement.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ayan.projectmanagement.dto.User;
import org.ayan.projectmanagement.dto.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate2;
	private static Logger LOG = LogManager.getLogger(UserDAO.class);

	public List<User> getUser(Integer userid) throws SQLException {
		String sql = "SELECT * FROM USERS WHERE USER_ID = :user_id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("user_id", userid);
		LOG.info("Search query :: " + sql);
		return jdbcTemplate.query(sql, params, new UserRowMapper());
	}

	public List<User> getAllUsers() throws SQLException {

		final String sql = "SELECT * FROM USERS";
		final List<User> users = new ArrayList<User>();
		final List<Map<String, Object>> rows = jdbcTemplate2.queryForList(sql);

		for (Map<String, Object> row : rows) {
			User user = new User();
			user.setUserid((int)row.get("USER_ID"));
			user.setFirstname((String)row.get("FIRST_NAME"));
			user.setLastname((String)row.get("LAST_NAME"));
			user.setEmpid((int)row.get("EMP_ID"));
			user.setProjectid((int)row.get("PROJECT_ID"));
			user.setTaskid((int)row.get("TASK_ID"));
			users.add(user);
		}
		return users;

	}
	
	public List<User> getAllUsersSortEmpById() throws SQLException {

		
		List<User> users = getAllUsers().stream().sorted((o1, o2) -> o1.getEmpid().compareTo(o2.getEmpid())).collect(Collectors.toList());
		
		return users;

	}
	
	public List<User> getAllUsersSortFname() throws SQLException {

		
		List<User> users = getAllUsers().stream().sorted((o1, o2) -> o1.getFirstname().toLowerCase().compareTo(o2.getFirstname().toLowerCase())).collect(Collectors.toList());
		
		return users;

	}
	public List<User> getAllUsersSortLname() throws SQLException {

		
		List<User> users = getAllUsers().stream().sorted((o1, o2) -> o1.getLastname().toLowerCase().compareTo(o2.getLastname().toLowerCase())).collect(Collectors.toList());
		
		return users;

	}
	
	public void addUser(User user) throws SQLException {
		LOG.info("Inside addUser");
		MapSqlParameterSource params = new MapSqlParameterSource();

		String sql = "INSERT INTO USERS(FIRST_NAME, LAST_NAME, EMP_ID, PROJECT_ID, TASK_ID) VALUES (:fname, :lname, :emp_id, :project_id, :task_id)";
		params.addValue("fname", user.getFirstname());
		params.addValue("lname", user.getLastname());
		params.addValue("emp_id", user.getEmpid());
		params.addValue("project_id", user.getProjectid());
		params.addValue("task_id", user.getTaskid());

		jdbcTemplate.update(sql, params);

	}

	public int deleteUser(Integer userid) {
		LOG.info("Inside deleteUser");
		MapSqlParameterSource params = new MapSqlParameterSource();
		String sql = "DELETE FROM USERS WHERE USER_ID = :user_id";
		params.addValue("user_id", userid);
		return jdbcTemplate.update(sql, params);
	}

	public int updateUser(User user) throws SQLException {
		LOG.info("Inside updateUser");
		MapSqlParameterSource params = new MapSqlParameterSource();

		String sql = "UPDATE USERS SET FIRST_NAME=:fname, LAST_NAME=:lname, EMP_ID=:emp_id, PROJECT_ID=:project_id, TASK_ID=:task_id WHERE USER_ID=:user_id";
		params.addValue("fname", user.getFirstname());
		params.addValue("lname", user.getLastname());
		params.addValue("emp_id", user.getEmpid());
		params.addValue("project_id", user.getProjectid());
		params.addValue("task_id", user.getTaskid());
		params.addValue("user_id", user.getUserid());
		return jdbcTemplate.update(sql, params);

	}
}
