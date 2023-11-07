package com.example.Registrationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Registrationservice.dto.RegistrationDto;
import com.example.Registrationservice.model.Registration;
import com.example.Registrationservice.service.RegistrationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
@CrossOrigin(origins = "http://localhost:4200",methods = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST})
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;
	
	@PostMapping("/addUser")
	public ResponseEntity<?> saveUser(@Valid @RequestBody Registration user) throws Exception {
		if(user.getRole().equals("Admin")||user.getRole().equals("User")) {
			return ResponseEntity.ok(registrationService.save(user));
		}
		else
		{
			return new ResponseEntity<>("Please Select a valid role",
                    HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@GetMapping("/AllData")
	public List<RegistrationDto> viewAll(){
		return registrationService.findAllAddress();
	}
	
}
