package com.techelevator.npgeek;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.ParkDAO;
import com.techelevator.npgeek.model.SurveyResult;
import com.techelevator.npgeek.model.SurveyResultDAO;
import com.techelevator.npgeek.model.Weather;
import com.techelevator.npgeek.model.WeatherDao;

@Controller
@SessionAttributes("userTempChoice")
public class HomePageController {

	@Autowired
	private WeatherDao weatherDao;

	@Autowired
	private ParkDAO parkDao;

	@RequestMapping(path = { "/", "/homepage" }, method = RequestMethod.GET)
	public String viewHomepage(ModelMap map) {
		List<Park> displayParks = parkDao.getAllParks();
		map.addAttribute("park", displayParks);
		return "homepage";
	}

	@RequestMapping(path = "/parkDetail", method = RequestMethod.GET)
	public String viewParkDetailPage(ModelMap map, @RequestParam String parkCode) {
		String newCode = parkCode.toUpperCase();
		map.addAttribute("park", parkDao.getParkByParkCode(newCode));

		List<Weather> displayWeather = weatherDao.getWeatherByParkCode(newCode);

		map.addAttribute("weather", displayWeather);

		return "parkDetail";
	}

	@RequestMapping(path = "/search", method = RequestMethod.GET)
	public String showSearchResult(ModelMap map, @RequestParam String search) {

		List<Park> showResult = parkDao.showResultBySearch(search);
		if (showResult.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		map.addAttribute("park", showResult);
		return "search";
	}

	@RequestMapping(path = "/parkDetail", method = RequestMethod.POST)
	public String viewParkDetailPageUserTempChoice(@RequestParam("selectedTempUnit") String selectedTempUnit,
			@RequestParam("parkCode") String parkCode, ModelMap modelMap) {

		modelMap.addAttribute("userTempChoice", selectedTempUnit);
		modelMap.addAttribute("parkCode", parkCode);
		return "redirect:/parkDetail";
	}

	@RequestMapping(path = "/errors", method = RequestMethod.GET)
	public String showSearchResult() {

		return "errors";
	}

}
