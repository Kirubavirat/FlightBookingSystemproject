package com.example.Availableflightservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.Availableflightservice.model.AvailableFlight;
@Repository
public interface AvailableFlightRepo extends MongoRepository<AvailableFlight,String> {
	
	List<AvailableFlight> findByFlightName(String flightName);
	List<AvailableFlight> findByFlightFromAndFlightToAndDate(String flightFrom, String flightTo, String date);
	List<AvailableFlight> findByFlightFrom(String flightFrom);

}
