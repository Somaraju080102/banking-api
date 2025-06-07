package com.spring.api.controller.mockbank;

import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mock-bank1")
public class MockBank1Controller {

	 @GetMapping("/account/{accountId}")
	    public ResponseEntity<Map<String, Object>> getAccountDetails(@PathVariable Long accountId) {
	        return ResponseEntity.ok(Map.of(
	            "accountId", accountId,
	            "holderName", "Ravi Sharma",
	            "balance", 10000,
	            "currency", "INR",
	            "status", "ACTIVE"
	        ));
	    }

	    @PostMapping("/transfer")
	    public ResponseEntity<Map<String, Object>> initiateTransfer(@RequestBody Map<String, Object> request) {
	        return ResponseEntity.ok(Map.of(
	            "transactionId", UUID.randomUUID().toString(),
	            "status", "SUCCESS",
	            "timestamp", System.currentTimeMillis()
	        ));
	    }
}
