package com.example.Registrationservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Registrationservice.dto.RegistrationDto;
import com.example.Registrationservice.dto.RegistrationDtoMapper;
import com.example.Registrationservice.exception.RegistrationException;
import com.example.Registrationservice.model.Registration;
import com.example.Registrationservice.repository.RegistrationRepository;

@Service
@Configuration
public class RegistrationService {
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private RegistrationDtoMapper registrationDtoMapper;
	
	@Autowired
	private RegistrationRepository registrationRepository;
	

	public Registration save(Registration user) {
		// TODO Auto-generated method stub
		Registration existingUser = registrationRepository.findByUsername(user.getUsername());

		if (existingUser != null) {
			// User with the same username already exists, throw an exception
			throw new RegistrationException("Username already exists: " + user.getUsername());
		}
		Registration login = new Registration();
		login.setUsername(user.getUsername());
		login.setPassword(bcryptEncoder.encode(user.getPassword()));
		login.setRole(user.getRole());
		login.setGender(user.getGender());
		login.setEmail(user.getEmail());
		login.setCountry(user.getCountry());
		login.setAge(user.getAge());
		return registrationRepository.save(login);
	}
	public List<RegistrationDto> findAllAddress(){
		return registrationRepository.findAll().stream().map(registrationDtoMapper).collect(Collectors.toList());
	}
	
		
	
}
