package com.techelevator.npgeek.weather;

import java.util.List;

public interface WeatherDAO {
	public List<Weather> getWeatherByCode(String parkCode);
}
