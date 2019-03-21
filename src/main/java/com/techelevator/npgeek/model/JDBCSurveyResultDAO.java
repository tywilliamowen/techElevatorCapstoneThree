package com.techelevator.npgeek.model;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCSurveyResultDAO implements SurveyResultDAO{

	
private JdbcTemplate jdbcTemplate;
	
    @Autowired
	public JDBCSurveyResultDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void saveSurvey(SurveyResult survey) {
		String sql= "INSERT INTO survey_result (parkcode, emailaddress, state, activitylevel) VALUES (?,?,?,?)";
		
		jdbcTemplate.update(sql,survey.getParkCode(), survey.getEmailAddress(),survey.getState(),survey.getActivityLevel());
		
	}
	
	

}
