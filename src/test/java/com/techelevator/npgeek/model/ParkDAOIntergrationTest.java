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

public  class ParkDAOIntergrationTest {

	/* Using this particular implementation of DataSource so that
	 * every database interaction is part of the same database
	 * session and hence the same database transaction */
	private static SingleConnectionDataSource dataSource;
	private ParkDAO parkdao; 
	private JdbcTemplate jdbcTemplate;
	
	
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
	public void get_all_parks() {
		int originalSize = parkdao.getAllParks().size(); 
		insertPark("1", "1", "1", 1, 1, 1.0, 1, "1", 1, 1, "1", "1", "1", 1, 1); 
		
		Assert.assertEquals("Wrong number of Parks Returned", originalSize + 1, parkdao.getAllParks().size());
	}

	@Test
	public void get_park_by_parkcode() {
		insertPark("t", "1", "1", 1, 1, 1.0, 1, "1", 1, 1, "1", "1", "1", 1, 1); 
		Assert.assertNotNull("Wrong number of Parks Returned",  parkdao.getParkByParkCode("t"));

	}
	
	@Test
	public void get_park_by_invalid_parkcode() {
		Assert.assertNull("Wrong number of Parks Returned",  parkdao.getParkByParkCode("t"));

	}
	
	
	@Test
	public void get_count_of_surveys() {
		int originalSize = parkdao.showSurveyPark().size(); 
		insertPark("t", "1", "1", 1, 1, 1.0, 1, "1", 1, 1, "1", "1", "1", 1, 1); 
		insertSurvey("t", "1@1.com", "OH", "Jean-Claude Van Damme"); 
		Assert.assertEquals("Wrong number of Surveys Returned", originalSize + 1, parkdao.showSurveyPark().size());

	}
	@Test
	public void search_park_by_parkcode() {
		int originalSize = parkdao.showResultBySearch("zzz").size(); 
		insertPark("zzz", "1", "1", 1, 1, 1.0, 1, "1", 1, 1, "1", "1", "1", 1, 1);
		Assert.assertEquals("Wrong number of Parks Returned By Search", originalSize + 1, parkdao.showResultBySearch("zzz").size());

	}
	@Test
	public void search_park_by_parkname() {
		int originalSize = parkdao.showResultBySearch("zzz").size(); 
		insertPark("1", "zzz", "1", 1, 1, 1.0, 1, "1", 1, 1, "1", "1", "1", 1, 1);
		Assert.assertEquals("Wrong number of Parks Returned By Search", originalSize + 1, parkdao.showResultBySearch("zzz").size());

	}
	@Test
	public void search_park_by_state() {
		int originalSize = parkdao.showResultBySearch("zzz").size(); 
		insertPark("1", "1", "zzz", 1, 1, 1.0, 1, "1", 1, 1, "1", "1", "1", 1, 1);
		Assert.assertEquals("Wrong number of Parks Returned By Search", originalSize + 1, parkdao.showResultBySearch("zzz").size());

	}
	@Test
	public void search_park_by_climate() {
		int originalSize = parkdao.showResultBySearch("zzz").size(); 
		insertPark("1", "1", "1", 1, 1, 1.0, 1, "zzz", 1, 1, "1", "1", "1", 1, 1);
		Assert.assertEquals("Wrong number of Parks Returned By Search", originalSize + 1, parkdao.showResultBySearch("zzz").size());

	}
	
	@Test
	public void search_park_by_quote() {
		int originalSize = parkdao.showResultBySearch("zzz").size(); 
		insertPark("1", "1", "1", 1, 1, 1.0, 1, "1", 1, 1, "zzz", "1", "1", 1, 1);
		Assert.assertEquals("Wrong number of Parks Returned By Search", originalSize + 1, parkdao.showResultBySearch("zzz").size());

	}
	
	@Test
	public void search_park_by_quotesource() {
		int originalSize = parkdao.showResultBySearch("zzz").size(); 
		insertPark("1", "1", "1", 1, 1, 1.0, 1, "1", 1, 1, "1", "zzz", "1", 1, 1);
		Assert.assertEquals("Wrong number of Parks Returned By Search", originalSize + 1, parkdao.showResultBySearch("zzz").size());

	}
	
	@Test
	public void search_park_by_park_description() {
		int originalSize = parkdao.showResultBySearch("zzz").size(); 
		insertPark("1", "1", "1", 1, 1, 1.0, 1, "1", 1, 1, "1", "1", "zzz", 1, 1);
		Assert.assertEquals("Wrong number of Parks Returned By Search", originalSize + 1, parkdao.showResultBySearch("zzz").size());

	}
	
	@Test
	public void no_parks_returned_from_invalid_search() {
		insertPark("1", "1", "1", 1, 1, 1.0, 1, "1", 1, 1, "1", "1", "1", 1, 1);
		Assert.assertEquals("Wrong number of Parks Returned By Search", 0,  parkdao.showResultBySearch("zzz").size());

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

	private void insertSurvey(String parkCode, String email, String state, String activityLevel) {
		String sql = "INSERT INTO survey_result (parkcode, emailaddress, state, activitylevel) VALUES (?,?,?,?)";
		jdbcTemplate.update(sql, parkCode, email, state,  activityLevel);
	}
	
	
}
