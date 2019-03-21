package com.techelevator.npgeek.model;



import java.sql.SQLException;


import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.npgeek.model.ParkDAO;
import com.techelevator.npgeek.model.SurveyResultDAO;
import com.techelevator.npgeek.model.WeatherDao;

public  class SurveyResultDAOIntergrationTest {

	/* Using this particular implementation of DataSource so that
	 * every database interaction is part of the same database
	 * session and hence the same database transaction */
	private static SingleConnectionDataSource dataSource;
	private SurveyResultDAO surveydao; 
	private ParkDAO parkdao; 
	private JdbcTemplate jdbcTemplate;
	private SurveyResult survey; 
	
	/* Before any tests are run, this method initializes the datasource for testing. */
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		/* The following line disables autocommit for connections 
		 * returned by this DataSource. This allows us to rollback
		 * any changes after each test */
		dataSource.setAutoCommit(false);
		
	}
	
	/* After all tests have finished running, this method will close the DataSource */
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}
	
	
	@Before
	public void before() {
		jdbcTemplate = new JdbcTemplate(dataSource); 
		surveydao = new JDBCSurveyResultDAO(dataSource); 
		survey = new SurveyResult(); 
		parkdao = new JDBCParkDAO(dataSource); 
	}

	/* After each test, we rollback any changes that were made to the database so that
	 * everything is clean for the next test */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	/* This method provides access to the DataSource for subclasses so that 
	 * they can instantiate a DAO for testing */
	public DataSource getDataSource() {
		return dataSource;
	}
	
	@Test
	public void save_a_survey() {
		int originalSize = parkdao.showSurveyPark().size(); 
		insertPark("1", "1", "1", 1, 1, 1.0, 1, "1", 1, 1, "1", "1", "1", 1, 1);
		survey.setActivityLevel("active");
		survey.setEmailAddress("testingonly@realemail.com");
		survey.setParkCode("1");
		survey.setState("OH");
		surveydao.saveSurvey(survey);
		
		Assert.assertEquals("Wrong number of Surveys Returned", originalSize + 1, parkdao.showSurveyPark().size());

	}
	
	
	private void insertPark(String parkCode, String parkName, String state,  
			int acreage, int elevation, double milesOfTrail, int campsties, 
			String climate, int yearFounded, int annualVisitors, String quote, 
			String quoteSource, String description, int fee, int animals) {
		String sql = "INSERT INTO park (parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, "
				+ "numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, "
				+ "inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, parkCode, parkName, state, acreage, elevation, milesOfTrail, campsties, climate, 
				yearFounded, annualVisitors, quote, quoteSource, description, fee, animals); 
	}
}
