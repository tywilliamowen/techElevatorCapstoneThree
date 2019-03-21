package com.techelevator.npgeek.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCWeatherDAO implements WeatherDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCWeatherDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Weather> getWeatherByParkCode(String parkCode) {
		List<Weather> weather= new ArrayList<Weather>(); 
		
		String sql = "SELECT parkcode, fivedayforecastvalue, low, high, forecast FROM weather WHERE "
				+ "parkcode=?"; 
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql,parkCode); 
		
		while (results.next()) {
			Weather w = mapRowToWeather(results); 
			weather.add(w); 
		}
		return weather;
	}


	
	private Weather mapRowToWeather(SqlRowSet results) {
		Weather weather = new Weather(); 
		weather.setParkCode(results.getString("parkcode"));
		weather.setFiveDayForecastValue(results.getInt("fivedayforecastvalue"));
		weather.setLow(results.getInt("low"));
		weather.setHigh(results.getInt("high"));
		weather.setForecast(results.getString("forecast"));
		
		return weather; 
	}

}
