package org.ayan.projectmanagement.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ayan.projectmanagement.dto.ParentTask;
import org.ayan.projectmanagement.dto.ParentTaskRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ParentTaskDAO {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate2;
	private static Logger LOG = LogManager.getLogger(ParentTaskDAO.class);

	public List<ParentTask> getParentTask(Integer parentid) throws SQLException {
		String sql = "SELECT * FROM PARENT_TASK WHERE PARENT_ID = :parent_id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("parent_id", parentid);
		LOG.info("Search query :: " + sql);
		return jdbcTemplate.query(sql, params, new ParentTaskRowMapper());
	}

	public void addParentTask(ParentTask parentTask) throws SQLException {
		LOG.info("Inside addParentTask");
		MapSqlParameterSource params = new MapSqlParameterSource();
		String sql = "INSERT INTO PARENT_TASK(PARENT_TASK_DESC) VALUES (:description)";
		params.addValue("description", parentTask.getDescription());
		jdbcTemplate.update(sql, params);

	}

	public int deleteParentTask(Integer parentid) {
		LOG.info("Inside deleteParentTask");
		MapSqlParameterSource params = new MapSqlParameterSource();
		String sql = "DELETE FROM PARENT_TASK WHERE PARENT_ID = :parent_id";
		params.addValue("parent_id", parentid);
		return jdbcTemplate.update(sql, params);
	}

	public int updateParentTask(ParentTask parentTask) throws SQLException {
		LOG.info("Inside updateParentTask");
		MapSqlParameterSource params = new MapSqlParameterSource();
		String sql = "UPDATE PARENT_TASK SET PARENT_TASK_DESC = :description WHERE PARENT_ID = :parent_id";
		params.addValue("parent_id", parentTask.getId());
		params.addValue("description", parentTask.getDescription());
		return jdbcTemplate.update(sql, params);

	}
}
