package com.example.wedding.domain.guestbook;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GuestbookRepository extends JpaRepository<Guestbook, Long> {
    List<Guestbook> findByWeddingId(Long weddingId);
}