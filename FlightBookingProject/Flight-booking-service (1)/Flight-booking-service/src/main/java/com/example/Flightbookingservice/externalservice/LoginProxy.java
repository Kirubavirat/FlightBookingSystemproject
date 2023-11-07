package com.example.Flightbookingservice.externalservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Flightbookingservice.externalclass.LoginModel;

@FeignClient(name = "LOGINSERVICE", url="http://localhost:9004")
public interface LoginProxy {
	
	@GetMapping("/registration/autherization/getbyUsername/{username}")
	public LoginModel getbyUserName(@PathVariable String username);

}
