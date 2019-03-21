package com.techelevator.npgeek.cukes;


import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import com.techelevator.npgeek.pageobject.HomePage;
import com.techelevator.npgeek.pageobject.SurveyInputPage;
import com.techelevator.npgeek.pageobject.SurveyResultPage;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ParkSurveyCukes {

	private WebDriver webDriver;
	private HomePage homepage;
	private SurveyInputPage surveyInput;
	private SurveyResultPage surveyResult;
	
	@Autowired
	public ParkSurveyCukes(WebDriver webDriver) {
		this.webDriver= webDriver;
		this.homepage = new HomePage(webDriver);
		this.surveyInput = new SurveyInputPage(webDriver);
		this.surveyResult = new SurveyResultPage(webDriver);
	}
	
	@Before
	public void setup() {
		webDriver.manage().deleteAllCookies();
		webDriver.get("http://localhost:8080/m3-java-capstone/");
	}
	
	@Given("^I am going to import new survey$")
	public void i_am_going_to_import_new_survey() throws Throwable{
		webDriver.get("http://localhost:8080/m3-java-capstone/");
		homepage.clickSurveyLink();
		}
	
	@Given("^I am going to choose park from List (.*)$")
	public void i_am_going_to_choose_park_from_List(String parkName) throws Throwable {
		surveyInput.chooseFavPark(parkName);
		
	}
	
	@Given("^I am going to enter an Email Address (.*)$")
	public void i_am_going_to_enter_an_Email_Address(String emailAddress) throws Throwable {
		surveyInput.enterAnEmail(emailAddress);	
	}
	
	@Given("^I am going to choose a State (.*)$")
	public void i_am_going_to_choose_a_state(String state) throws Throwable {
		surveyInput.chooseSate(state);
	}
	
	@Given("^I am going to choose a activity level (.*)$")
	public void i_am_going_to_choose_activity_level(String activityLevel) throws Throwable {
		surveyInput.chooseActivityLevel(activityLevel);	
	}
	@When("^I request to submit a survey form")
	public void i_request_to_submit_a_survey_form() throws Throwable{
		surveyInput.submitForm();
	}
	
	@Then("^The Matching park is (.*)$")
	public void matching_park_after_submit_form(String parkName) throws Throwable{
		surveyResult.findPark();
	}

}
