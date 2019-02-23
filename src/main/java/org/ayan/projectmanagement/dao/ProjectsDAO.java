package org.ayan.projectmanagement.dao;


import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ayan.projectmanagement.dto.Projects;
import org.ayan.projectmanagement.dto.ProjectsRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectsDAO {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate2;
	private static Logger LOG = LogManager.getLogger(ProjectsDAO.class);

	public List<Projects> getProjects(Integer projectid) throws SQLException {
		String sql = "SELECT * FROM PROJECTS WHERE PROJECT_ID = :project_id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("project_id", projectid);
		LOG.info("Search query :: " + sql);
		return jdbcTemplate.query(sql, params, new ProjectsRowMapper());
	}

	public void addProjects(Projects projects) throws SQLException {
		LOG.info("Inside addProjects");
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		Date start_date = null;
		Date end_date = null;
		try {
			LOG.info("Start date "+projects.getStartDate()+ "End date "+ projects.getEndDate());
			start_date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(projects.getStartDate());
			end_date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(projects.getEndDate());
		} catch(ParseException e) {
			LOG.error(e);
		}
		
		String sql = "INSERT INTO PROJECTS(PROJECT_DESC, START_DATE, END_DATE, PRIORTY) VALUES (:desc, :sdate, :edate, :prt)";
		params.addValue("desc", projects.getDescription());
		params.addValue("sdate", start_date);
		params.addValue("edate", end_date);
		params.addValue("prt", projects.getPriority());
		jdbcTemplate.update(sql, params);

	}

	public int deleteProjects(Integer projectid) {
		LOG.info("Inside deleteProjects");
		MapSqlParameterSource params = new MapSqlParameterSource();
		String sql = "DELETE FROM PROJECTS WHERE PROJECT_ID = :project_id";
		params.addValue("project_id", projectid);
		return jdbcTemplate.update(sql, params);
	}

	public int updateProjects(Projects projects) throws SQLException {
		LOG.info("Inside updateProjects");
		MapSqlParameterSource params = new MapSqlParameterSource();
		Date start_date = null;
		Date end_date = null;
		try {
			LOG.info("Start date "+projects.getStartDate()+ "End date "+ projects.getEndDate());
			start_date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(projects.getStartDate());
			end_date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(projects.getEndDate());
		} catch(ParseException e) {
			LOG.error(e);
		}
		
		String sql = "UPDATE PROJECTS SET PROJECT_DESC = :desc, START_DATE=:sdate, END_DATE=:edate, PRIORTY=:prt  WHERE PROJECT_ID = :project_id";
		params.addValue("desc", projects.getDescription());
		params.addValue("sdate", start_date );
		params.addValue("edate", end_date);
		params.addValue("prt", projects.getPriority());
		params.addValue("project_id", projects.getProjectid());
		return jdbcTemplate.update(sql, params);

	}
}
