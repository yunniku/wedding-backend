package com.example.wedding.domain.wedding;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WeddingRepository extends JpaRepository<Wedding, Long> {
    Wedding findByShareCode(String shareCode);
}