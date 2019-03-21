package com.techelevator.npgeek.selenium;

import static org.junit.Assert.assertEquals;

import javax.naming.directory.SearchResult;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.techelevator.npgeek.pageobject.HomePage;
import com.techelevator.npgeek.pageobject.ParkDetailTempCheckPage;
import com.techelevator.npgeek.pageobject.SearchResultPage;
import com.techelevator.npgeek.pageobject.SurveyInputPage;
import com.techelevator.npgeek.pageobject.SurveyResultPage;

public class ParkSeleniumTest {

	private static WebDriver webDriver;
	private HomePage homePage;

	@BeforeClass
	public static void openWebBrowserForTesting() {

		String homeDir = System.getProperty("user.home");
		System.setProperty("webdriver.chrome.driver", homeDir + "/dev-tools/chromedriver/chromedriver");
		webDriver = new ChromeDriver();
	}

	@Before
	public void openHomePage() {
		webDriver.get("http://localhost:8080/m3-java-capstone/");
		homePage = new HomePage(webDriver);
	}

	@AfterClass
	public static void closeWebBrowser() {
		webDriver.close();
	}

	//Test For Survey Form
	@Test
	public void submit_survey_form() {
		SurveyResultPage resultPage = homePage.clickSurveyLink().chooseFavPark("Rocky Mountain National Park")
				.enterAnEmail("test@gmail.com").chooseSate("California").chooseActivityLevel("activityLevel3")
				.submitForm();
		assertEquals("Rocky Mountain National Park",resultPage.findPark());
	}

	//Test for Temp in F in Park Detail
	@Test
	public void check_temp_in_Fahrenheit_in_park_details() {
		ParkDetailTempCheckPage checkTemp = homePage.clickParkLinkToGoToParkDetail()
				.chooseTemp("selectedTempUnit", "Fahrenheit").submitForm();

		assertEquals("High: 62 °F", checkTemp.findTempertureInF("side1"));
		assertEquals("Low: 38 °F", checkTemp.findTempertureInF("side2"));
	}

	//Test for Temp in C in Park Detail
	@Test
	public void check_temp_in_Celsius_in_park_details() {
		ParkDetailTempCheckPage checkTemp = homePage.clickParkLinkToGoToParkDetail()
				.chooseTemp("selectedTempUnit", "Celsius").submitForm();

		assertEquals("High: 16.66 °C", checkTemp.findTempertureInCelsius("side1"));
		assertEquals("Low: 3.33 °C", checkTemp.findTempertureInCelsius("side2"));
	}
	
	//Test for search bar if word exist
	@Test
	public void search_form_for_exist_word() {
		SearchResultPage search = homePage.serachBar("Cuyahoga").clickSearch();
		assertEquals("Cuyahoga Valley National Park",search.searchResult("Cuyahoga Valley National Park"));
	}
	
	//Test for search bar if word doesn't exist and when it redirect to errors page
	@Test
	public void search_form_for_error_page() {
		SearchResultPage search = homePage.serachBar("hjsdhwdhuwu").clickSearch();
		assertEquals("BACK TO HOMEPAGE",search.searchResult("BACK TO HOMEPAGE"));
	}
}
