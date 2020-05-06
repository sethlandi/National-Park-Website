package com.techelevator.npgeek.survey;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcSurveyDAO implements SurveyDAO {

	private JdbcTemplate jdbcTemplate;

	public JdbcSurveyDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Survey> getParksBySurvey() {
		List<Survey> surveys = new ArrayList<Survey>();

		String sqlGetParksBySurvey = "SELECT park.parkname,survey_result.parkcode, park.parkdescription , count(survey_result.surveyid) "
									+ "FROM park "
									+ "INNER JOIN survey_result ON park.parkcode = survey_result.parkcode "
									+ "GROUP BY park.parkname, survey_result.parkcode, park.parkdescription "
									+ "ORDER BY count(survey_result.surveyid) desc";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetParksBySurvey);

		while (results.next()) {
			Survey aSurvey = mapRowToSurvey(results);
			surveys.add(aSurvey);
		}

		return surveys;
	}

	@Override
	public void save(Survey survey) {
		String sqlInsertSurvey = "INSERT INTO survey_result(surveyid, parkcode, emailaddress, state, activitylevel) "
							   + "VALUES(?, ?, ?, ?, ?)";
		
		Long id = getNextId();	
		jdbcTemplate.update(sqlInsertSurvey, id, survey.getParkCode(), survey.getEmailAddress(), survey.getState(), survey.getActivityLevel());
		survey.setSurveyId(id);
	}
		
	private Long getNextId() {
		String sqlSelectNextId = "SELECT NEXTVAL('seq_surveyid')";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectNextId);
		if (result.next()) {
			return result.getLong(1);
		} else {
			throw new RuntimeException("Something went wrong while getting the next product id");
		}
	}
	
	private Survey mapRowToSurvey(SqlRowSet results) {

		Survey aSurvey = new Survey();
		
		aSurvey.setParkName(results.getString("parkname"));
		aSurvey.setParkDescription(results.getString("parkDescription"));
		aSurvey.setParkCode(results.getString("parkcode"));
		aSurvey.setParkCount(results.getLong("count"));
		return aSurvey;
	}
}
