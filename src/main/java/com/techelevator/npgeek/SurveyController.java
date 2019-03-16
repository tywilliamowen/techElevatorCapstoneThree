package com.techelevator.npgeek;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.ParkDAO;
import com.techelevator.npgeek.model.SurveyResult;
import com.techelevator.npgeek.model.SurveyResultDAO;

@Controller
public class SurveyController {
	
	@Autowired
	private ParkDAO parkDao;
	
	@Autowired
	private SurveyResultDAO surveyDao;

	@RequestMapping(path= "/survey", method=RequestMethod.GET)
	public String viewSurveyForm(ModelMap map) {
		if (!map.containsAttribute("survey") ) {
			map.addAttribute("survey", new SurveyResult());
		}
		
		return "survey"; 
	}
	
	@RequestMapping(path= "/survey", method=RequestMethod.POST)
	public String viewSurveyForm(@Valid @ModelAttribute("survey")  SurveyResult surveyResult, BindingResult result, ModelMap map) {
		
		if(result.hasErrors()) {
			return "survey"; 
		}
		
		surveyDao.saveSurvey(surveyResult);
		map.addAttribute("survey", surveyResult);
		return "redirect:/surveyResults"; 
	}
	
	
	@RequestMapping(path= "/surveyResults", method=RequestMethod.GET)
	public String viewSurveyResults(ModelMap map) {
		
		List<Park> showServey = parkDao.showSurveyPark();
		map.addAttribute("park",showServey);
		
		return "surveyResults"; 
	}
}
