package com.example.Availableflightservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.Availableflightservice.exception.FlightNotFoundException;
import com.example.Availableflightservice.model.AvailableFlight;
import com.example.Availableflightservice.repository.AvailableFlightRepo;



@SpringBootTest
public class FlightServiceTest {
	
	@Autowired
	private AvailableFlightService flightServiceImpl;

	@MockBean
	private AvailableFlightRepo flightRepository;

	@Test
	public void addFlightModel_test() {
		AvailableFlight flight = new AvailableFlight("12345", "indigo", "chennai", "Hyderabad", 700, 50,"06/11/2023","7:45 AM");
		when(flightRepository.save(flight)).thenReturn(flight);
		System.out.println(flightServiceImpl.addAvailableFlight(flight) + "*****************************");
		System.out.println(flight + "Error is there ******************************");
		assertEquals(flight, flightServiceImpl.addAvailableFlight(flight));
	}

	@Test
	public void addFlightModel_MissingFields() {
		AvailableFlight flight = new AvailableFlight("", "indigo", "chennai", "Hyderabad", 700, 50,"06/11/2023","7:45 AM");

		// Call the service method and expect a FlightNotFoundException
		assertThrows(FlightNotFoundException.class, () -> {
			flightServiceImpl.addAvailableFlight(flight);
		});
	}

	@Test
	public void getFlights_DataFound() {
		List<AvailableFlight> flight = new ArrayList<>();



		flight.add(new AvailableFlight("12345", "indigo", "chennai", "Hyderabad", 700, 50,"06/11/2023","7:45 AM"));
		flight.add(new AvailableFlight("67890", "airthai", "india", "Thailand", 900, 40,"06/11/2023","4:35 PM"));

		when(flightRepository.findAll()).thenReturn(flight);
		assertEquals(2, flightServiceImpl.getAllAvailableFlights().size());
	}

	@Test
	public void getFlightByFlightNo_DataFound() {
		AvailableFlight t = new AvailableFlight("12345", "indigo", "chennai", "Hyderabad", 700, 50,"06/11/2023","7:45 AM");
		when(flightRepository.findById("12345")).thenReturn(Optional.of(t));
		Optional<AvailableFlight> result = Optional.of(flightServiceImpl.getFlightById("12345"));
		assertEquals(t, result.get());
	}

	@Test
	public void getFlightByFlightNo_DataNotFound() {
	    when(flightRepository.findById("NotFound")).thenReturn(Optional.empty());
	    
	    // Call the service method and expect a FlightNotFoundException
	    assertThrows(FlightNotFoundException.class, () -> {
	        flightServiceImpl.getFlightById("Flight NotFound");
	    });
	}

	@Test
	public void getFlightsByName_DataFound() {
		AvailableFlight flight = new AvailableFlight("12345", "indigo", "chennai", "Hyderabad", 700, 50,"06/11/2023","7:45 AM");
		when(flightRepository.findByFlightName("indigo")).thenReturn(List.of(flight));
		List<AvailableFlight> result = flightServiceImpl.getFlightsbyname("indigo");
		assertEquals(flight, result.get(0));
	}

	@Test
	public void getFlightsByName_DataNotFound() {
		
	    when(flightRepository.findByFlightName("NotFound")).thenReturn(new ArrayList<>());
	    
	    // Call the service method and expect a FlightNotFoundException
	    assertThrows(FlightNotFoundException.class, () -> {
	        flightServiceImpl.getFlightsbyname("Flight NotFound");
	    });
	}

	@Test
	public void findByLocation_DataFound() {
		AvailableFlight flight = new AvailableFlight("12345", "indigo", "chennai", "Hyderabad", 700, 50,"06/11/2023","7:45 AM");
		List<AvailableFlight> expectedResults = List.of(flight);
		when(flightRepository.findByFlightFromAndFlightToAndDate("chennai", "Hyderabad","06/11/2023")).thenReturn(expectedResults);
		List<AvailableFlight> result = flightServiceImpl.findByLocation("chennai", "Hyderabad","06/11/2023");
		assertEquals(expectedResults, result);
	}

	@Test
	public void findByLocation_DataNotFound() {
	  
	    when(flightRepository.findByFlightFromAndFlightToAndDate("aaa", "bbb","09/11/2023")).thenReturn(new ArrayList<>());

	    // Call the service method and expect a FlightNotFoundException
	    assertThrows(FlightNotFoundException.class, () -> {
	        flightServiceImpl.findByLocation("aaa", "bbb","09/11/2023");
	    });
	}
	
	@Test
	public void deleteFlight_Exists() {
	    AvailableFlight flight = new AvailableFlight("12345", "indigo", "chennai", "Hyderabad", 700, 50,"06/11/2023","7:45 AM");

	    when(flightRepository.findById("12345")).thenReturn(Optional.of(flight));
	    String result = flightServiceImpl.deleteFlight("12345");
	    assertEquals("Flight is deleted Successfully", result);
	}

	@Test
	public void deleteFlight_NotExists() {
	    // Stub the behavior of flightRepository.findById to return an empty Optional (flight doesn't exist)
	    when(flightRepository.findById("09876")).thenReturn(Optional.empty());

	    // Call the service method and expect a FlightNotFoundException
	    assertThrows(FlightNotFoundException.class, () -> {
	        flightServiceImpl.deleteFlight("09876");
	    });
	}

}
