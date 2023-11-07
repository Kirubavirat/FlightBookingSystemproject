package com.example.Registrationservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.Registrationservice.model.Registration;


@Repository
public interface RegistrationRepository extends MongoRepository<Registration,String>{

	Registration findByUsername(String username);

}

