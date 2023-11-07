package com.example.Flightbookingservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;


import com.example.Flightbookingservice.model.BookingModel;
import com.example.Flightbookingservice.repository.BookingRepository;



@SpringBootTest
class FlightBookingServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired

	BookingRepository  bookingRepository;
	
	
	@Test

	@Order(1)

	public void saveBooking() {

		BookingModel booking = new BookingModel();

		booking.setUsername("kiruba");
		booking.setFlightNo("99001");
		booking.setPhnnumber("9876543450");
		booking.setEmail("kiruba123@gmail.com");
		booking.setNumberOfTickets(2);
		booking.setCost(600);

		BookingModel save = bookingRepository.save(booking);

		assertNotNull(save);

	}
	
	@Test

	@Order(2)

	public void cancelBooking() {

		bookingRepository.findById("09876");

		assertThat(bookingRepository.existsById("09876")).isFalse();

	}
	@Test

	@Order(3)

	public void getAllBookings() {

		List<BookingModel> list = bookingRepository.findAll();

		assertThat(list).size().isGreaterThan(0);

	}
	
	@Test

	@Order(4)

	public void getBookingByPnr() {

		BookingModel booking = bookingRepository.findById("e9809438-83a0-460d-b6a6-1daa051e68bc").get();

		assertEquals("e9809438-83a0-460d-b6a6-1daa051e68bc", booking.getPnr());

	}



}
