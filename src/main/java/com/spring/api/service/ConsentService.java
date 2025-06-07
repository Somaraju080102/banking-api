package com.spring.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.api.dto.ConsentRequestDTO;
import com.spring.api.dto.ConsentResponseDTO;
import com.spring.api.entity.Consent;
import com.spring.api.entity.User;
import com.spring.api.repo.ConsentRepository;
import com.spring.api.repo.UserRepo;

@Service
public class ConsentService {
	
	@Autowired
    private ConsentRepository consentRepo;

    @Autowired
    private UserRepo userRepo;

    public ConsentResponseDTO grantConsent(ConsentRequestDTO dto) {
        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Consent consent = new Consent();
        consent.setUser(user);
        consent.setDataType(dto.getDataType());
        consent.setGranted(dto.isGranted());

        Consent saved = consentRepo.save(consent);

        ConsentResponseDTO response = new ConsentResponseDTO();
        response.setId(saved.getId());
        response.setDataType(saved.getDataType());
        response.setGranted(saved.getGranted());
        response.setCreatedAt(saved.getCreatedAt());

        return response;
    }

    public List<Consent> getUserConsents(Long userId) {
        return consentRepo.findByUserId(userId);
    }

    public void revokeConsent(Long id) {
        consentRepo.deleteById(id);
    }

}
