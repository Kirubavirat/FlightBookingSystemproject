package com.example.Availableflightservice.service;

import java.util.List;



import com.example.Availableflightservice.exception.FlightNotFoundException;
import com.example.Availableflightservice.model.AvailableFlight;

public interface AvailableFlightService {
	public AvailableFlight addAvailableFlight (AvailableFlight flight) throws FlightNotFoundException;
	public List<AvailableFlight> getAllAvailableFlights() throws FlightNotFoundException;
	public AvailableFlight getFlightById(String flightNo) throws FlightNotFoundException;
	public String deleteFlight(String flightNo) throws FlightNotFoundException;
	public List<AvailableFlight> findByLocation(String flightFrom,String flightTo,String date) throws FlightNotFoundException;
	public List<AvailableFlight> getFlightsbyname(String flightName) throws FlightNotFoundException;
	public AvailableFlight updateFlight(String flightNo, AvailableFlight flight) throws FlightNotFoundException;

}
