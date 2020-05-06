package com.techelevator.npgeek.parks;

import java.util.List;

public interface ParkDAO {
	public List<Park> getAllParks();

	public Park getParkByCode(String parkCode);
	
}
