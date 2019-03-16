package com.techelevator.npgeek.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Weather {
	private String parkCode;
	private int fiveDayForecastValue;
	private int low;
	private int high;
	private String forecast;
	private String forecastDayOfWeek;
	private String showDegree;

	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}

	public int getFiveDayForecastValue() {
		return fiveDayForecastValue;
	}

	public void setFiveDayForecastValue(int fiveDayForecastValue) {
		this.fiveDayForecastValue = fiveDayForecastValue;
	}

	public int getLow() {
		return low;
	}

	public void setLow(int low) {
		this.low = low;
	}

	public int getHigh() {
		return high;
	}

	public void setHigh(int high) {
		this.high = high;
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		
		
		this.forecast = forecast;
	}

	public String getAdvisoryMessage() {
		return getAdvisoryMesasge();
	}

	public double getHighCelsius() {
		return convertFToC(high);
	}

	public double getLowCelsius() {
		return convertFToC(low);
	}

// J-Unit Test Needed
	public String getAdvisoryMesasge() {
		String returnString = "";
		if (forecast.equals("snow")) {
			returnString = returnString + "Snow Advisory: pack snowshoes. ";
		} else if (forecast.equals("rain")) {
			returnString = returnString + "Rain Advisory: pack rain gear and wear waterproof shoes. ";
		} else if (forecast.equals("thunderstorms")) {
			returnString = returnString + "Thunderstorms Advisory: seek shelter and avoid hiking on exposed ridges. ";
		} else if (forecast.equals("sunny")) {
			returnString = returnString + "Sun Advisory: pack sunblock. ";
		}

		if (high > 75) {
			returnString = returnString + "High Temperature Advisory: bring an extra gallon of water. ";
		}

		if (low < 20) {
			returnString = returnString
					+ "Low Temperature Advisory: increased risk of injury due to cold temperatures. ";
		}

		if (Math.abs(high - low) > 20) {
			returnString = returnString + "Temperature Fluctuation Advisory: wear breathable layers. ";

		}
		if (returnString.equals("")) {
			returnString = "No Weather Advisory Today.";
		}

		return returnString;
	}

// J-Unit Test Needed	
	private double convertFToC(int temp) {
		return Math.floor(((temp - 32) / 1.8) * 100) / 100;

	}

	public String getForecastDayOfWeek() {

		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		calendar.add(Calendar.DAY_OF_WEEK, fiveDayForecastValue - 1);
		Date tomorrow = calendar.getTime();
		String day;
		if (fiveDayForecastValue == 1) {

			SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
			day = simpleDateformat.format(tomorrow);
			day = "Today";

		} else if (fiveDayForecastValue == 2) {

			SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
			day = simpleDateformat.format(tomorrow);
			day = "Tomorrow";

		} else {

			SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
			day = simpleDateformat.format(tomorrow);

		}

		return day;

	}
	
	public String getShowDegree() {
		return showDegree = "\u00B0";
	}

}
