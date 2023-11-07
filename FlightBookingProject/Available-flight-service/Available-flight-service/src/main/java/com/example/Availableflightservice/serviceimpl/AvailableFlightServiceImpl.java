package com.example.Availableflightservice.serviceimpl;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Availableflightservice.exception.FlightNotFoundException;
import com.example.Availableflightservice.model.AvailableFlight;
import com.example.Availableflightservice.repository.AvailableFlightRepo;
import com.example.Availableflightservice.service.AvailableFlightService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AvailableFlightServiceImpl implements AvailableFlightService {
	
	@Autowired
	 AvailableFlightRepo availableFlightRepo;
	
	
	@Override
	public AvailableFlight addAvailableFlight(AvailableFlight flight) throws FlightNotFoundException {
		// TODO Auto-generated method stub
	log.info("Add Method Started");
		
		    if (flight.getFlightNo().isEmpty() ||
		    		flight.getFlightName().isEmpty() ||
		    		flight.getFlightFrom().isEmpty() ||
		    		flight.getFlightTo().isEmpty() ||
		    		flight.getTime().isEmpty() ||
		    		flight.getDate().isEmpty() ||
		    		flight.getSeats() ==0 ||
		    		flight.getFare() == 0) {
		    	log.info("Inside the if condition of Add method");
		        throw new FlightNotFoundException("Please fill every field appropriately");
		   }
		    else {
		    log.info("Inside the else condition of Add method");
		    return availableFlightRepo.save(flight);
		    }
	}

	@Override
	public List<AvailableFlight> getAllAvailableFlights() throws FlightNotFoundException {
		// TODO Auto-generated method stub
		log.info("Get All Flights Method Started");
		List<AvailableFlight> findAll = availableFlightRepo.findAll();
		if(!findAll.isEmpty()) {
			log.info("Inside the if condition of getAllFlight method");
			return findAll;
		}
		else {
			log.warn("Inside the else condition of getAllFlight method");
			throw new FlightNotFoundException("No data is found");
		}
	}

	@Override
	public AvailableFlight getFlightById(String flightNo) throws FlightNotFoundException {
		// TODO Auto-generated method stub
		Optional<AvailableFlight> optionalFlightModel = availableFlightRepo.findById(flightNo);
	    log.info("Get Flight By Id Method Started");

	    if (optionalFlightModel.isPresent()) {
	        log.info("Inside the if condition of getFlightById method");
	        return optionalFlightModel.get();
	    } else {
	        log.info("Inside the else condition of getFlightById method");
	        throw new FlightNotFoundException("Flight with ID " + flightNo + " not found");
	    }
	}

	@Override
	public String deleteFlight(String flightNo) throws FlightNotFoundException {
		// TODO Auto-generated method stub
		log.info("Delete Train Method Started");
		Optional<AvailableFlight> optionalFlightModel = availableFlightRepo.findById(flightNo);
		
		if (optionalFlightModel.isPresent()) {
			availableFlightRepo.deleteById(flightNo);
		log.info("Inside the if condition of Delete method");
		return "Flight is deleted Successfully";
		}
		else {
			throw new FlightNotFoundException("Flight is not exists with the Flight Id "+flightNo);
		}
	}

	@Override
	public List<AvailableFlight> findByLocation(String flightFrom, String flightTo, String date)
			throws FlightNotFoundException {
		// TODO Auto-generated method stub
		log.info("find By Location Method is started ");
		System.out.println("******************************"+flightFrom+""+flightTo+""+date);
		List<AvailableFlight> flightModels = availableFlightRepo.findByFlightFromAndFlightToAndDate(flightFrom, flightTo,date);
		System.out.println("******************************Error is here"+availableFlightRepo.findByFlightFromAndFlightToAndDate(flightFrom, flightTo,date));
		if(flightModels.isEmpty()) {
			log.info("Inside the if condition of findByLocation method");
			throw new FlightNotFoundException("No data is found for these destination");
		}
		else {
			log.warn("Inside the else condition of findByLocation method");
			return flightModels;
		}
	}

	@Override
	public List<AvailableFlight> getFlightsbyname(String flightName) throws FlightNotFoundException {
		// TODO Auto-generated method stub
		log.info("Get Flight By Name Method is started");
		List<AvailableFlight> flightModels = availableFlightRepo.findByFlightName(flightName);
		if(flightModels.isEmpty()) {
			log.warn("Inside the if condition of getFlightsbyname method");
			throw new FlightNotFoundException("No data is found by these "+flightName);
		}
		else {
			log.info("Inside the else condition of getFlightsbyname method");
			return flightModels;
		}
	}

	@Override
	public AvailableFlight updateFlight(String flightNo, AvailableFlight flight) throws FlightNotFoundException {
		// TODO Auto-generated method stub
		log.info("Update Flight method started");
		 Optional<AvailableFlight> findById =availableFlightRepo.findById(flightNo);
		if(findById.isPresent()) {
			log.info("Inside the if condition of updateFlight method");
			AvailableFlight updateflight = findById.get();
			updateflight.setFlightName(flight.getFlightName());
			updateflight.setFlightFrom(flight.getFlightFrom());
			updateflight.setFlightTo(flight.getFlightTo());
			updateflight.setSeats(flight.getSeats());
			updateflight.setFare(flight.getFare());
			updateflight.setDate(flight.getDate());
			updateflight.setTime(flight.getTime());
			
			 AvailableFlight updated = availableFlightRepo.save(flight);
			 log.info("Flight updated Successfully");
			return updated;
		}
		else {
			log.info("Inside the else condition of updateFlight method");
			throw new FlightNotFoundException("It doesn't exists for modification");
		}
	}

}
