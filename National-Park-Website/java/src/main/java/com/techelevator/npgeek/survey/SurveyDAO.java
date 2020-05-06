package com.techelevator.npgeek.survey;

import java.util.List;

public interface SurveyDAO {
	
	public List<Survey> getParksBySurvey();
	
	public void save(Survey survey);
}
