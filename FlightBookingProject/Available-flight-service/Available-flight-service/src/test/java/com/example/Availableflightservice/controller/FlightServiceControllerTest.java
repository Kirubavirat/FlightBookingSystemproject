package com.example.Availableflightservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.example.Availableflightservice.model.AvailableFlight;
import com.example.Availableflightservice.service.AvailableFlightService;

@SpringBootTest
public class FlightServiceControllerTest {
	
	@Autowired
	private AvailableFlightController flightcontroller;

	@MockBean
	private AvailableFlightService flightservice;
	
	@Test
	public void addAvailableFlight_test() {
		AvailableFlight flight = new AvailableFlight("87654", "indigo", "chennai", "Hyderabad", 700, 20, "15/11/2023","10:00 AM");
		when(flightservice.addAvailableFlight(flight)).thenReturn(flight);
		ResponseEntity<AvailableFlight> response = flightcontroller.addFlightModel(flight);
		assertNotNull(response);
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(flight, response.getBody());
	}
	
	@Test
	public void getAllFlights_test() {
		List<AvailableFlight> flight = new ArrayList<>();
		flight.add(new AvailableFlight("87654", "indigo", "chennai", "Hyderabad", 700, 20, "15/11/2023","10:00 AM"));
		flight.add(new AvailableFlight("87655", "airasia", "chennai", "mumbai", 700, 20, "15/11/2023","7:45 AM"));

		when(flightservice.getAllAvailableFlights()).thenReturn(flight);
		ResponseEntity<List<AvailableFlight>> response = flightcontroller.getAllFlights();
		List<AvailableFlight> flights = response.getBody();
		assertEquals(2, flights.size());
	}
	
	  @Test
	  public void getFlightById_test(){
		AvailableFlight t = new AvailableFlight("87654", "indigo", "chennai", "Hyderabad", 700, 20, "15/11/2023","10:00 AM");
		when(flightservice.getFlightById("87654")).thenReturn(t);
		ResponseEntity<AvailableFlight> response=flightcontroller.getFlightByNo("87654");
		AvailableFlight flight = response.getBody();
		assertEquals(t, flight);
	}
	  

	 
	  @Test
	  public void getFlightByName_test()  {
	      
	      List<AvailableFlight> flightList = new ArrayList<>();
	      flightList.add(new AvailableFlight("87654", "indigo", "chennai", "Hyderabad", 700, 20, "15/11/2023","10:00 AM"));
	        flightList.add(new AvailableFlight("87655", "airasia", "chennai", "mumbai", 700, 20, "15/11/2023","7:45 AM"));

	      List<AvailableFlight> filteredList = new ArrayList<>();
	      for (AvailableFlight flight : flightList) {
	          if ("indigo".equals(flight.getFlightName())) {
	              filteredList.add(flight);
	          }
	      }

	      when(flightservice.getFlightsbyname("indigo")).thenReturn(filteredList);

	      ResponseEntity<List<AvailableFlight>> response = flightcontroller.getFlightsbyname("indigo");

	      assertNotNull(response);
	      List<AvailableFlight> flights = response.getBody();
	      assertEquals(1, flights.size());
	      assertEquals("indigo", flights.get(0).getFlightName());
	  }


	  @Test
	    public void deleteFlight_test() {
	        
	        String flightNo = "87655";

	        String expectedResponse = "Flight with number " + flightNo + " has been deleted successfully";

	        when(flightservice.deleteFlight(flightNo)).thenReturn(expectedResponse);

	        ResponseEntity<String> response = flightcontroller.deleteFlight(flightNo);

	        assertNotNull(response);

	        assertEquals(200, response.getStatusCodeValue());

	        assertEquals(expectedResponse, response.getBody());
	    }
	
	  @Test
	    public void findByLocation_test() {
	        
	        String flightFrom = "chennai";
	        String flightTo = "Hyderabad";
	        String date = "15/11/2023";

	        List<AvailableFlight> flightList = new ArrayList<>();
	        flightList.add(new AvailableFlight("87654", "indigo", "chennai", "Hyderabad", 700, 20, "15/11/2023","10:00 AM"));
	        flightList.add(new AvailableFlight("87655", "airasia", "chennai", "mumbai", 700, 20, "15/11/2023","7:45 AM"));

	        List<AvailableFlight> filteredList = new ArrayList<>();
		      for (AvailableFlight flight : flightList) {
		          if (flightFrom.equals(flight.getFlightFrom()) && flightTo.equals(flight.getFlightTo()) && date.equals(flight.getDate())) {
		              filteredList.add(flight);
		          }
		      }
	        
	        when(flightservice.findByLocation(flightFrom, flightTo, date)).thenReturn(filteredList);

	        ResponseEntity<List<AvailableFlight>> response = flightcontroller.findByLocation(flightFrom, flightTo, date);

	        assertNotNull(response);

	        assertEquals(200, response.getStatusCodeValue());

	        List<AvailableFlight> flights = response.getBody();
	        assertNotNull(flights);
	        assertEquals(1, flights.size());
	        
	    }
	  
	  @Test
	    public void updateFlight_test() {
	        String flightNo = "87654";

	        AvailableFlight updatedFlight = new AvailableFlight(flightNo, "UpdatedTrain", "Warangal", "Hyderabad", 800, 30,"15/11/2023" ,"7:45 AM");

	        when(flightservice.updateFlight(flightNo, updatedFlight)).thenReturn(updatedFlight);

	        ResponseEntity<AvailableFlight> response = flightcontroller.updateFlight(flightNo, updatedFlight);

	        assertNotNull(response);

	        assertEquals(200, response.getStatusCodeValue());

	        assertEquals(updatedFlight, response.getBody());
	    }

}
