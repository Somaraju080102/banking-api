package com.spring.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {
	
	
	@GetMapping("/")
	public String message() {
		
		return "hello";
	}
}
