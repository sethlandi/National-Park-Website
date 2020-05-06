package com.techelevator.npgeek;

import java.util.ArrayList; 
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.npgeek.User.User;
import com.techelevator.npgeek.authentication.AuthProviderDAO;
import com.techelevator.npgeek.parks.Park;
import com.techelevator.npgeek.parks.ParkDAO;
import com.techelevator.npgeek.register.Register;
import com.techelevator.npgeek.register.RegisterDAO;
import com.techelevator.npgeek.survey.Survey;
import com.techelevator.npgeek.survey.SurveyDAO;
import com.techelevator.npgeek.weather.Weather;
import com.techelevator.npgeek.weather.WeatherDAO;

@Controller 
public class NpgController {

	@Autowired
	private ParkDAO theParks;
	
	@Autowired
	private WeatherDAO theWeather;
	
	@Autowired
	private SurveyDAO theSurvey;
	
	@Autowired
	private RegisterDAO theRegister;
	
	@Autowired
    private AuthProviderDAO auth;
	
	@RequestMapping(path= {"/", "loginInput"}, method=RequestMethod.GET)
	public String showLoginInput(Model modelHolder) {
		if (!modelHolder.containsAttribute("User")) {
			modelHolder.addAttribute("User", new User());
		}
		return "loginInput";
	}
	
	@RequestMapping(path="/loginInput", method=RequestMethod.POST)
	public String submitLoginInput(@Valid @ModelAttribute("User") User submitUserValues,
								   BindingResult result, RedirectAttributes flash){
		
		if (auth.signIn(submitUserValues)) {
			if(result.hasErrors()) { 
				flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "User", result);						
				flash.addFlashAttribute("User", submitUserValues);
				
				return "redirect:/loginInput"; 
			}
			else {	
				flash.addFlashAttribute("message", "Welcome back Wonderer!");
		
				return "redirect:/homePage";
			}
		}	
		else {
			flash.addFlashAttribute("message", "Login Invalid");
				
			return "redirect:/loginInput";
		}
	}
	
	@RequestMapping(path = "/logoff", method = RequestMethod.POST)
    public String logOff() {
        auth.logOff();
        return "redirect:/loginInput";
    }    
	
	@RequestMapping(path="/registerInput", method=RequestMethod.GET)
	public String showRegisterInput(ModelMap map) {
		
		if( ! map.containsAttribute("Register")) { 
			map.addAttribute("Register", new Register()); 
		}
		return "registerInput";
	}
	
	@RequestMapping(path="/registerInput", method=RequestMethod.POST)
	public String processRegisterInput(@Valid @ModelAttribute("Register") Register submitRegisterValues,
																		  ModelMap map,
																		  BindingResult result,
																		  RedirectAttributes flash){

		if(result.hasErrors()) { 
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "Register", result);
			flash.addFlashAttribute("Register", submitRegisterValues);
			
			return  "redirect:/registerInput";
		}
				
		flash.addFlashAttribute("message", "Thanks for your submission.");			
		theRegister.save(submitRegisterValues);
		
		return "redirect:/loginInput";
	}
	

	@RequestMapping(path="/homePage", method=RequestMethod.GET)
	public String showHomePage(HttpSession sharedWithView) {
		sharedWithView.setAttribute("user", auth.getCurrentUser());
		
		List<Park> listOfParks = theParks.getAllParks();	
		sharedWithView.setAttribute("parks", listOfParks);	
		
		return "homePage";
	}
	
	@RequestMapping(path="/parkDetail", method=RequestMethod.GET)
	public String showParkInfo(@RequestParam String parkCode
            				  , HttpSession sharedWithView) {
		
		Park theParkSelected = theParks.getParkByCode(parkCode);
		sharedWithView.setAttribute("aPark", theParkSelected);	
		
		List<Weather> weatherList = new ArrayList<Weather>();
		weatherList = theWeather.getWeatherByCode(parkCode);	
		sharedWithView.setAttribute("weather", weatherList);
		
		return "parkDetail";
	}
	
	@RequestMapping(path="/surveyInput", method=RequestMethod.GET)
	public String showSurveyInput(ModelMap map){
	
		if( ! map.containsAttribute("Survey")) { 
			map.addAttribute("Survey", new Survey()); 
		}
	
		List<Park> listOfParks = theParks.getAllParks();	
		map.addAttribute("parks", listOfParks);	
		
		
		return "surveyInput";
	}
	
	@RequestMapping(path="/surveyInput", method=RequestMethod.POST)
	public String processSurveyInput(@Valid @ModelAttribute("Survey") Survey submitSurveyValues,
																	  BindingResult result,
																	  RedirectAttributes flash){
	
		if(result.hasErrors()) { 
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "Survey", result);
			flash.addFlashAttribute("Survey", submitSurveyValues);
			
			return  "redirect:/surveyInput";
		}
				
		flash.addFlashAttribute("message", "Thanks for your submission.");			
		theSurvey.save(submitSurveyValues);
		
		return "redirect:/surveyResult";
	}
	
	@RequestMapping(path = "/surveyResult", method=RequestMethod.GET)
	public String showParkRanks(ModelMap map) {
	
		List<Survey> posts = theSurvey.getParksBySurvey();
		map.put("posts", posts);
		
		List<Park> listOfParks = theParks.getAllParks();	
		map.addAttribute("parks", listOfParks);	
		
		return "surveyResult";
	}
}