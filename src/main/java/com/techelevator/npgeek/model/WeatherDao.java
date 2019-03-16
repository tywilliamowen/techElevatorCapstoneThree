package com.techelevator.npgeek.model;

import java.util.List;

public interface WeatherDao {
	
	public List<Weather> getWeatherByParkCode(String parkCode);	

}
