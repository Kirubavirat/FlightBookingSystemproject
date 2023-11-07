package com.example.Registrationservice.dto;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.example.Registrationservice.model.Registration;

@Component
public class RegistrationDtoMapper  implements Function<Registration ,RegistrationDto>{

	@Override
	public RegistrationDto apply(Registration t) {
		// TODO Auto-generated method stub
		return new RegistrationDto(
				t.getUsername(),
				t.getRole(),
				t.getEmail(),
				t.getGender(),
				t.getAge(),
				t.getCountry()
				);
	}
}