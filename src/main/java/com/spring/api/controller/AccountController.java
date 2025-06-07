package com.spring.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.api.dto.AccountBalanceResponse;
import com.spring.api.dto.AccountRequest;
import com.spring.api.dto.AccountResponse;
import com.spring.api.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	 @Autowired
	    private AccountService accountService;

	    @PostMapping
	    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest request,
	            @AuthenticationPrincipal UserDetails userDetails) {
	        return ResponseEntity.ok(accountService.createAccount(request, userDetails.getUsername()));
	    }

	    @GetMapping
	    public ResponseEntity<List<AccountResponse>> getAccounts(
	            @AuthenticationPrincipal UserDetails userDetails) {
	        return ResponseEntity.ok(accountService.getAccounts(userDetails.getUsername()));
	    }
	
	    @GetMapping("/balance")
	    public ResponseEntity<List<AccountBalanceResponse>> getBalances(@AuthenticationPrincipal UserDetails userDetails) {
	        String userEmail = userDetails.getUsername();  // assuming username = email
	        List<AccountBalanceResponse> balances = accountService.getAccountBalances(userEmail);
	        return ResponseEntity.ok(balances);
	    }
	

}
