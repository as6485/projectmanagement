package org.ayan.projectmanagement.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ayan.projectmanagement.dto.Task;
import org.ayan.projectmanagement.dto.TaskRowMapper;
import org.ayan.projectmanagement.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TaskDAO {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate2;
	private static Logger LOG = LogManager.getLogger(TaskDAO.class);

	public List<Task> getTask(Integer taskid) throws SQLException {
		String sql = "SELECT * FROM TASK WHERE TASK_ID = :task_id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("task_id", taskid);
		LOG.info("Search query :: " + sql);
		return jdbcTemplate.query(sql, params, new TaskRowMapper());
	}

	public void addTask(Task task) throws SQLException {
		LOG.info("Inside addTask");
		MapSqlParameterSource params = new MapSqlParameterSource();

		Date start_date = null;
		Date end_date = null;
		try {
			LOG.info("Start date " + task.getStartDate() + "End date " + task.getEndDate());
			start_date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(task.getStartDate());
			end_date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(task.getEndDate());
		} catch (ParseException e) {
			LOG.error(e);
		}

		String sql = "INSERT INTO TASK(PARENT_ID, PROJECT_ID, TASK_DESC, START_DATE, END_DATE, PRIORTY, TASK_STATUS) "
				+ "VALUES (:parent_id, :project_id, :desc, :sdate, :edate, :prt, :status)";

		params.addValue("parent_id", task.getParentid());
		params.addValue("project_id", task.getProjectid());
		params.addValue("desc", task.getDescription());
		params.addValue("sdate", start_date);
		params.addValue("edate", end_date);
		params.addValue("prt", task.getPriority());
		params.addValue("status", task.isStatus());
		jdbcTemplate.update(sql, params);

	}

	public int deleteTask(Integer taskid) {
		LOG.info("Inside deleteTask");
		MapSqlParameterSource params = new MapSqlParameterSource();
		String sql = "DELETE FROM TASK WHERE TASK_ID = :taskid";
		params.addValue("taskid", taskid);
		return jdbcTemplate.update(sql, params);
	}

	public int updateTask(Task task) throws SQLException {
		LOG.info("Inside updateTask");
		MapSqlParameterSource params = new MapSqlParameterSource();
		Date start_date = null;
		Date end_date = null;
		try {
			LOG.info("Start date " + task.getStartDate() + "End date " + task.getEndDate());
			start_date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(task.getStartDate());
			end_date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(task.getEndDate());
		} catch (ParseException e) {
			LOG.error(e);
		}

		String sql = "UPDATE TASK SET PARENT_ID = :parent_id, PROJECT_ID = :project_id, TASK_DESC = :desc, START_DATE=:sdate,"
				+ " END_DATE=:edate, PRIORTY=:prt , TASK_STATUS=:status WHERE TASK_ID = :task_id";
		params.addValue("parent_id", task.getParentid());
		params.addValue("project_id", task.getProjectid());
		params.addValue("desc", task.getDescription());
		params.addValue("sdate", start_date);
		params.addValue("edate", end_date);
		params.addValue("prt", task.getPriority());
		params.addValue("status", task.isStatus());
		params.addValue("task_id", task.getTaskid());
		return jdbcTemplate.update(sql, params);

	}

	public List<Task> getAllTasks() throws SQLException {

		final String sql = "SELECT * FROM TASK";
		final List<Task> tasks = new ArrayList<Task>();
		final List<Map<String, Object>> rows = jdbcTemplate2.queryForList(sql);

		for (Map<String, Object> row : rows) {
			Task task = new Task();
			task.setTaskid((int) row.get("TASK_ID"));
			task.setParentid((int) row.get("PARENT_ID"));
			task.setProjectid((int) row.get("PROJECT_ID"));
			task.setDescription((String) row.get("TASK_DESC"));
			task.setStartDate(String.valueOf((Date) row.get("START_DATE")));
			task.setEndDate(String.valueOf((Date) row.get("END_DATE")));
			task.setPriority((int) row.get("PRIORTY"));
			task.setStatus((boolean) row.get("TASK_STATUS"));
			tasks.add(task);

		}
		return tasks;

	}

}
