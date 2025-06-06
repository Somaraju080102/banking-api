package com.spring.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.api.dto.AuthResponse;
import com.spring.api.dto.LoginRequest;
import com.spring.api.dto.SignupRequest;
import com.spring.api.service.AuthService;

import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	
	 	@Autowired
	    private AuthService authService;

	    @PostMapping("/signup")
	    public ResponseEntity<AuthResponse> signup(@RequestBody SignupRequest req) {
	    	System.out.println("JWT Version: " + Jwts.builder().getClass().getPackage().getImplementationVersion());

	        return ResponseEntity.ok(authService.signup(req));
	    }

	    @PostMapping("/login")
	    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest req) {
	        return ResponseEntity.ok(authService.login(req));
	    }
	    
}
