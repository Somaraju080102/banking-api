package com.spring.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {
	
	
	@GetMapping("/")
	public String message() {
		
		return "hello";
	}
	
	@GetMapping("/api/public-info")
	public String publicApi() {
	    return "This is visible to browser GET";
	}
}
