package com.spring.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.api.dto.ConsentRequestDTO;
import com.spring.api.dto.ConsentResponseDTO;
import com.spring.api.entity.Consent;
import com.spring.api.service.ConsentService;

@RestController
@RequestMapping("/api/consents")
public class ConsentController {
	
	@Autowired
    private ConsentService consentService;

    @PostMapping("/grant")
    public ResponseEntity<ConsentResponseDTO> grantConsent(@RequestBody ConsentRequestDTO dto) {
        return ResponseEntity.ok(consentService.grantConsent(dto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Consent>> getUserConsents(@PathVariable Long userId) {
        return ResponseEntity.ok(consentService.getUserConsents(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> revokeConsent(@PathVariable Long id) {
        consentService.revokeConsent(id);
        return ResponseEntity.noContent().build();
    }

}
