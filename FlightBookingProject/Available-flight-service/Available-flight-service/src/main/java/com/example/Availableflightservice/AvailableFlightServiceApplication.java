package com.example.Availableflightservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class AvailableFlightServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvailableFlightServiceApplication.class, args);
	}

}
