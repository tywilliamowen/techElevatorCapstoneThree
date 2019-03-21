Feature: import new Survey

	Scenario: Importing Survey in Database
		Given I am going to import new survey
		And I am going to choose park from List Rocky Mountain National Park
		And I am going to enter an Email Address test6676767368722763676763@gmail.com
		And I am going to choose a State Georgia
		And I am going to choose a activity level activityLevel3
		When I request to submit a survey form
		Then The Matching park is Rocky Mountain National Park