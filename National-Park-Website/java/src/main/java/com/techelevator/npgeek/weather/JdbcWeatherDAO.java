package com.techelevator.npgeek.weather;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcWeatherDAO implements WeatherDAO {

	private JdbcTemplate jdbcTemplate;

	public JdbcWeatherDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override
	public List<Weather> getWeatherByCode(String parkCode) {
    	
		List<Weather> weather = new ArrayList<Weather>();
    	
    	String sqlGetWeatherByCode = "SELECT parkcode, fivedayforecastvalue, low, ((low - 32) * 5/9) as lowC, high, ((high - 32) * 5/9) as highC, REPLACE(forecast, 'partly cloudy', 'partlyCloudy') as forecast "
    							   + "FROM weather "
    							   + "WHERE parkcode = ?";
    	
    	SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetWeatherByCode, parkCode);

    	    while (results.next()) {
    	        Weather aWeather = (mapRowToWeather(results));
    	        weather.add(aWeather);
    	    }
    	    
    	    return weather;
    	}
	private Weather mapRowToWeather(SqlRowSet results) {

		Weather theWeather = new Weather();

		theWeather.setParkCode(results.getString("parkcode"));
		theWeather.setFiveDayForecast(results.getInt("fivedayforecastvalue"));
		theWeather.setLow(results.getInt("low"));
		theWeather.setLowC(results.getInt("lowC"));
		theWeather.setHigh(results.getInt("high"));
		theWeather.setHighC(results.getInt("highC"));
		theWeather.setForecast(results.getString("forecast"));
		
		return theWeather;

	}

}
