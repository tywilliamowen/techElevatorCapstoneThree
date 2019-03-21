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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		List<Park> displayParks = parkDao.getAllParks();
		map.addAttribute("park", displayParks);
		
		return "survey"; 
	}
	
	@RequestMapping(path= "/survey", method=RequestMethod.POST)
	public String viewSurveyForm(@Valid @ModelAttribute("survey")  SurveyResult surveyResult, BindingResult result, ModelMap map, RedirectAttributes attr) {
		
		if(result.hasErrors()) {
			List<Park> displayParks = parkDao.getAllParks();
			map.addAttribute("park", displayParks);
			return "survey"; 
		}
		
		surveyDao.saveSurvey(surveyResult);
		map.addAttribute("survey", surveyResult);
		attr.addFlashAttribute("surveyUrl",surveyResult);
		attr.addFlashAttribute("votecast",", Your vote has been cast!");
		attr.addFlashAttribute("thankyou","Thank you");
		return "redirect:/surveyResults"; 
	}
	
	@RequestMapping(path= "/parksurvey", method=RequestMethod.GET)
	public String viewParkSurvey(ModelMap map, @RequestParam String parkCode) {
		if (!map.containsAttribute("survey") ) {
			map.addAttribute("survey", new SurveyResult());
		}
		String newCode = parkCode.toUpperCase();
		map.addAttribute("park", parkDao.getParkByParkCode(newCode));
		
		return "parksurvey"; 
	}
	
	
	@RequestMapping(path= "/parksurvey", method=RequestMethod.POST)
	public String surveyForSpecificPark(@Valid @ModelAttribute("survey")  SurveyResult surveyResult, BindingResult result, ModelMap map, @RequestParam String parkCode,RedirectAttributes attr) {
		
		if(result.hasErrors()) {
			String newCode = parkCode.toUpperCase();
			map.addAttribute("park", parkDao.getParkByParkCode(newCode));
			return "parksurvey"; 
		}
		
		surveyDao.saveSurvey(surveyResult);
		map.addAttribute("survey", surveyResult);
		attr.addFlashAttribute("surveyUrl",surveyResult);
		attr.addFlashAttribute("votecast",", Your vote has been cast!");
		attr.addFlashAttribute("thankyou","Thank you");
		return "redirect:/surveyResults"; 
	}
	
	
	@RequestMapping(path= "/surveyResults", method=RequestMethod.GET)
	public String viewSurveyResults(ModelMap map) {
		
		List<Park> showServey = parkDao.showSurveyPark();
		map.addAttribute("park",showServey);
		
		return "surveyResults"; 
	}
}
