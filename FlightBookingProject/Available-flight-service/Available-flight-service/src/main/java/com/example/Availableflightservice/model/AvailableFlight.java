package com.example.Availableflightservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "FlightService")
public class AvailableFlight {

    @Id
    @Pattern(regexp = "\\d{5}", message = "Flight number should contain exactly 5 digits")
    private String flightNo;

    @NotBlank(message = "flightName cannot be empty")
    @Size(min = 3, max = 50, message = "flightName should be between 3 and 50 characters")
    private String flightName;

    @NotBlank(message = "flightFrom cannot be empty")
    @Size(min = 3, max = 30, message = "flightFrom should be between 3 and 30 characters")
    private String flightFrom;

    @NotBlank(message = "flightTo cannot be empty")
    @Size(min = 3, max = 30, message = "flightTo should be between 3 and 30 characters")
    private String flightTo;

    @Min(value = 0, message = "fare should be a positive number")
    @Max(value = 1000, message = "fare should not exceed 1000")
    private int fare;

    @Min(value = 0, message = "seats should be a positive number")
    @Max(value = 50, message = "seats should not exceed 50")
    private int seats;
    
    @NotBlank(message = "data cannot be empty")
    private String date;

    @NotBlank(message = "time cannot be empty")
    private String time;
}
