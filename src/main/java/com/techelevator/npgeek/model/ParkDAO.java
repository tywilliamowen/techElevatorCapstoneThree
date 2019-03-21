package com.techelevator.npgeek.model;

import java.util.List;

public interface ParkDAO {
	
	public List<Park> getAllParks(); 
	
	public Park getParkByParkCode(String parkCode); 
	
	public List<Park> showSurveyPark();
	
	public List<Park> showResultBySearch(String search);
}
