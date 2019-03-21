package com.techelevator.npgeek.model;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WeatherTest {
	private Weather weather; 
	
	@Before 
	public void setup() {
		weather = new Weather(); 
	}
 
	@Test
	public void convert_five_degrees_f_to_c() {

		weather.setHigh(5);
		weather.getHighCelsius(); 
		Assert.assertEquals(-15.00, weather.getHighCelsius(), 0.00);
	}
	
	
	@Test
	public void convert_11_degrees_f_to_c() {

		weather.setHigh(11);
		weather.getHighCelsius(); 
		Assert.assertEquals(-11.67, weather.getHighCelsius(), 0.00);
	}
	
	@Test
	public void convert_0_degrees_f_to_c() {

		weather.setHigh(0);
		weather.getHighCelsius(); 
		Assert.assertEquals(-17.78, weather.getHighCelsius(), 0.00);
	}
	
	@Test
	public void convert_negative_10_degrees_f_to_c() {

		weather.setHigh(-10);
		weather.getHighCelsius(); 
		Assert.assertEquals(-23.34, weather.getHighCelsius(), 0.00);
	}
	
	@Test
	public void get_snow_advisory_message() {
		weather.setHigh(50);
		weather.setLow(50);
		weather.setForecast("snow");
		Assert.assertEquals("Snow Advisory: pack snowshoes. ", weather.getAdvisoryMesasge());
	}
	
	@Test
	public void get_snow_and_low_temp_advisory_message() {
		weather.setHigh(2);
		weather.setLow(0);
		weather.setForecast("snow");
		Assert.assertEquals("Snow Advisory: pack snowshoes. Low Temperature Advisory: increased risk of injury due to cold temperatures. ", weather.getAdvisoryMesasge());
	}
	@Test
	public void get_rain_and_low_temp_advisory_message() {
		weather.setHigh(80);
		weather.setLow(59);
		weather.setForecast("rain");
		Assert.assertEquals("Rain Advisory: pack rain gear and wear waterproof shoes. High Temperature Advisory: bring an extra gallon of water. Temperature Fluctuation Advisory: wear breathable layers. ", weather.getAdvisoryMesasge());
	}
	
	@Test
	public void get_no_weather_advisory_message() {
		weather.setHigh(50);
		weather.setLow(50);
		weather.setForecast("");
		Assert.assertEquals("No Weather Advisory Today.", weather.getAdvisoryMesasge());
	}
	
	
	@Test
	public void get_today_as_day() {
	 weather.setFiveDayForecastValue(1);
	 Assert.assertEquals("Today", weather.getForecastDayOfWeek());
	}
	
	@Test
	public void get_tomorrow_as_day() {
	 weather.setFiveDayForecastValue(2);
	 Assert.assertEquals("Tomorrow", weather.getForecastDayOfWeek());
	}
	
	@Test
	public void get_third_day_as_day() {
	 weather.setFiveDayForecastValue(3);
	 LocalDate date = LocalDate.now().plusDays(2);
	 date.getDayOfWeek().name();
	 Assert.assertEquals(date.getDayOfWeek().name(), weather.getForecastDayOfWeek().toUpperCase());
	}
	
	
	
}

