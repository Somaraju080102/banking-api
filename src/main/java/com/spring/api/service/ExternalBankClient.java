package com.spring.api.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.spring.api.dto.TransferRequest;

@Component
public class ExternalBankClient {
	
	 private final RestTemplate restTemplate = new RestTemplate();

	    public boolean transferToExternalBank(String bankName, Map<String, Object> transferPayload) {
	        String url = resolveBankUrl(bankName);

	        if (url == null) return false;

	        try {
	            HttpHeaders headers = new HttpHeaders(null);
	            headers.setContentType(MediaType.APPLICATION_JSON);
	            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(transferPayload, headers);

	            ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);

	            if (response.getStatusCode() == HttpStatus.OK && "SUCCESS".equalsIgnoreCase((String) response.getBody().get("status"))) {
	                return true;
	            }
	        } catch (Exception e) {
	            System.out.println("External transfer failed: " + e.getMessage());
	        }

	        return false;
	    }

	    private String resolveBankUrl(String bankName) {
	        return switch (bankName.toUpperCase()) {
	            case "MOCK_BANK1" -> "http://localhost:8080/mock-bank1/transfer";
	            case "MOCK_BANK2" -> "http://localhost:8080/mock-bank2/transfer";
	            default -> null;
	        };
	    }
	    public boolean sendMoneyToExternalBank(TransferRequest req) {
	        // Dummy logic
	        return req.getReceiverAccountId() % 2 == 0; // even = success
	    }

}
