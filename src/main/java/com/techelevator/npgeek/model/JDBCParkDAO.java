package com.techelevator.npgeek.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;



@Component
public class JDBCParkDAO implements ParkDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Park> getAllParks() {
		List<Park> parks = new ArrayList<Park>(); 
		
		String sql ="SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, "
				+ "numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, "
				+ "inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies FROM park "
				+ "ORDER BY parkname ASC"; 
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql); 
		
		while (results.next()) {
			Park p = mapRowToPark(results); 
			parks.add(p); 
		}
		return parks;
	}

	@Override
	public Park getParkByParkCode(String parkCode) {
		Park returnPark = null;  
		
		String sql ="SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies FROM park WHERE parkcode = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkCode); 
					
		if(results.next()) {
			returnPark = mapRowToPark(results);
		}
		return returnPark;
	}
	
	@Override
	public List<Park> showSurveyPark() {
		List<Park> showServey = new ArrayList<Park>();

		String sql = "SELECT parkname , park.parkcode, count(parkname) AS totalVotes from park JOIN survey_result on park.parkcode = survey_result.parkcode group by parkname,park.parkcode order by totalVotes Desc, parkname ASC";
	
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
		
		while(result.next()) {
			Park p = mapRowToParkSurvey(result);
			showServey.add(p);
		}
		return showServey;
	}
	
	
	@Override
	public List<Park> showResultBySearch(String search) {
		List<Park> showSearchResult = new ArrayList<Park>();
		
		
		String sqlSearch ="SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, "
				+ "numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, "
				+ "inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies FROM park "
				+ "WHERE parkname ILIKE ? "
				+ "OR parkcode ILIKE ? OR state ILIKE ? OR parkdescription ILIKE ?"
				+ "OR climate ILIKE ? OR inspirationalquote ILIKE ? OR inspirationalquotesource ILIKE ?";
			
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSearch,"%"+search+"%","%"+search+"%","%"+search+"%","%"+search+"%","%"+search+"%","%"+search+"%","%"+search+"%");
		
		while(result.next()) {
			showSearchResult.add(mapRowToPark(result));
			
		}
				return showSearchResult;
	}
	

	private Park mapRowToPark(SqlRowSet results) {
		Park park = new Park(); 
		park.setParkCode(results.getString("parkcode"));
		park.setParkName(results.getString("parkname"));
		park.setState(results.getString("state"));
		park.setAcerage(results.getInt("acreage"));
		park.setElevationInFeet(results.getInt("elevationinfeet"));
		park.setMilesOfTrail(results.getDouble("milesoftrail"));
		park.setNumberOfCampsites(results.getInt("numberofcampsites"));
		park.setClimate(results.getString("climate"));
		park.setYearsFounded(results.getInt("yearfounded"));
		park.setAnnualVisitorCount(results.getInt("annualvisitorcount"));
		park.setInspirationalQuote(results.getString("inspirationalquote"));
		park.setInspirationalQuoteSource(results.getString("inspirationalquotesource"));
		park.setParkDescription(results.getString("parkdescription"));
		park.setEntryFee(results.getInt("entryfee"));
		park.setNumberOfAnimalSpecies(results.getInt("numberofanimalspecies"));
	
		return park; 
	}

	
	private Park mapRowToParkSurvey(SqlRowSet results) {
		Park park = new Park(); 
		park.setParkName(results.getString("parkname"));
		park.setParkCode(results.getString("parkcode"));
		park.setVotes(results.getInt("totalVotes"));
		return park;
	}

	
	
}
