package com.spring.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.api.dto.AuthResponse;
import com.spring.api.dto.LoginRequest;
import com.spring.api.dto.SignupRequest;
import com.spring.api.entity.User;
import com.spring.api.repo.UserRepo;
import com.spring.api.utils.JwtUtil;

@Service
public class AuthService {
	
	@Autowired
	private UserRepo userRepository;
	
		@Autowired
	    private PasswordEncoder passwordEncoder;

	    @Autowired
	    private JwtUtil jwtUtil;

	    public AuthResponse signup(SignupRequest req) {
	        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
	            throw new RuntimeException("User already exists");
	        }

	        User user = new User();
	        user.setName(req.getName());
	        user.setEmail(req.getEmail());
	        user.setPassword(passwordEncoder.encode(req.getPassword()));
	        userRepository.save(user);

	        String token = jwtUtil.generateToken(user.getEmail());
	        return new AuthResponse(token);
	    }

	    public AuthResponse login(LoginRequest req) {
	        User user = userRepository.findByEmail(req.getEmail())
	                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

	        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
	            throw new RuntimeException("Invalid email or password");
	        }

	        String token = jwtUtil.generateToken(user.getEmail());
	        return new AuthResponse(token);
	    }

		
}
