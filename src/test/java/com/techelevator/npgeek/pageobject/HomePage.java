package com.techelevator.npgeek.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	
	private WebDriver webDriver;

	public HomePage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public SurveyInputPage clickSurveyLink() {
		WebElement surveyLink = webDriver.findElement(By.linkText("Survey"));
		surveyLink.click();
		return new SurveyInputPage(webDriver);
		
	}
	
	public ParkDetailTempCheckPage clickParkLinkToGoToParkDetail() {
		WebElement clickParkLink = webDriver.findElement(By.linkText("Cuyahoga Valley National Park"));
		clickParkLink.click();
		return new ParkDetailTempCheckPage(webDriver);
		
	}
	
	public HomePage serachBar(String parkName) {
		WebElement amountField = webDriver.findElement(By.name("search"));
		amountField.sendKeys(parkName);
		return this;
		
	}
	
	public SearchResultPage clickSearch() {
		WebElement submitSearch = webDriver.findElement(By.id("searchButton"));
		submitSearch.click();
		return new SearchResultPage(webDriver);
	}

}
