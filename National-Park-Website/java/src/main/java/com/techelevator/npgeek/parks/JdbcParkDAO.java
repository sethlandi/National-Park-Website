package com.techelevator.npgeek.parks;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcParkDAO implements ParkDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Park> getAllParks() {
			List<Park> parks = new ArrayList<Park>();
			String sqlGetAllParks = "SELECT * "
								  + "FROM park "
								  + "ORDER BY parkname";
			
			SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllParks);
			
			while (results.next()) {
				Park thePark = mapRowToPark(results);
				parks.add(thePark);
			}
			return parks;
	}
	
	 @Override
	    public Park getParkByCode(String parkCode) {	    	
	    	Park park = new Park ();	    	
	    	String sqlGetParkByCode = "Select * "
	    							+ "FROM park "
	    							+ "WHERE parkcode = ?";
	    	
	    	SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetParkByCode, parkCode);

	    	    if (results.next()) {
	    	        park = (mapRowToPark(results));
	    	    }	    	    
	    	    return park;
	    	}
	
	private Park mapRowToPark(SqlRowSet results) {

		Park thePark = new Park();

		thePark.setParkCode(results.getString("parkcode"));
		thePark.setParkName(results.getString("parkname"));
		thePark.setState(results.getString("state"));
		thePark.setAcreage(results.getLong("acreage"));
		thePark.setElevationInFeet(results.getInt("elevationinfeet"));
		thePark.setMilesOfTrail(results.getDouble("milesoftrail"));
		thePark.setNumberOfCampsites(results.getInt("numberofcampsites"));
		thePark.setClimate(results.getString("climate"));
		thePark.setYearFounded(results.getInt("yearfounded"));
		thePark.setAnnualVisitorCount(results.getInt("annualvisitorcount"));
		thePark.setInspirationalQuote(results.getString("inspirationalquote"));
		thePark.setInspirationalQuoteSource(results.getString("inspirationalquotesource"));
		thePark.setParkDescription(results.getString("parkdescription"));
		thePark.setEntryFee(results.getDouble("entryfee"));
		thePark.setNumberOfAnimalSpecies(results.getInt("numberofanimalspecies"));
		
		return thePark;

	}

}
