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

import com.spring.api.dto.TransactionResponse;
import com.spring.api.dto.TransferRequest;
import com.spring.api.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class TransactionController {
	
	
	
	 @Autowired
	    private TransactionService transactionService;

	    @PostMapping("/transfer")
	    public ResponseEntity<TransactionResponse> transfer(@Valid @RequestBody TransferRequest request, 
	                                                       @AuthenticationPrincipal UserDetails userDetails) {
	        String currentUserEmail = userDetails.getUsername();
	        TransactionResponse response = transactionService.transfer(request, currentUserEmail);
	        return ResponseEntity.ok(response);
	    }

	    @GetMapping("/transactions")
	    public ResponseEntity<List<TransactionResponse>> getTransactions(@AuthenticationPrincipal UserDetails userDetails) {
	        String currentUserEmail = userDetails.getUsername();
	        List<TransactionResponse> transactions = transactionService.getTransactionsForUser(currentUserEmail);
	        return ResponseEntity.ok(transactions);
	    }

}
