package com.spring.api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.api.entity.Consent;

@Repository
public interface ConsentRepository extends JpaRepository<Consent, Long> {
    List<Consent> findByUserId(Long userId);
}