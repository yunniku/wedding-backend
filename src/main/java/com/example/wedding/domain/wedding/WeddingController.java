package com.example.wedding.domain.wedding;

import com.example.wedding.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/weddings")
@RequiredArgsConstructor
public class WeddingController {

    private final WeddingRepository weddingRepository;
    private final UserRepository userRepository;

    private final com.example.wedding.domain.guestbook.GuestbookRepository guestbookRepository;

    // 청첩장 생성
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Wedding wedding,
                                    @RequestParam Long userId) {
        userRepository.findById(userId).ifPresent(wedding::setUser);
        wedding.setShareCode(UUID.randomUUID().toString().substring(0, 8));
        weddingRepository.save(wedding);
        return ResponseEntity.ok(wedding);
    }

    // 내 청첩장 목록
    @GetMapping
    public ResponseEntity<List<Wedding>> getAll() {
        return ResponseEntity.ok(weddingRepository.findAll());
    }

    // 공유 코드로 청첩장 조회 (방문자용)
    @GetMapping("/share/{shareCode}")
    public ResponseEntity<?> getByShareCode(@PathVariable String shareCode) {
        Wedding wedding = weddingRepository.findByShareCode(shareCode);
        if (wedding == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(wedding);
    }

    // 청첩장 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody Wedding updated) {
        return weddingRepository.findById(id).map(wedding -> {
            wedding.setGroomName(updated.getGroomName());
            wedding.setBrideName(updated.getBrideName());
            wedding.setWeddingDate(updated.getWeddingDate());
            wedding.setVenue(updated.getVenue());
            wedding.setVenue2(updated.getVenue2());
            wedding.setMessage(updated.getMessage());
            wedding.setGroomPhone(updated.getGroomPhone());
            wedding.setBridePhone(updated.getBridePhone());
            wedding.setGroomAccount(updated.getGroomAccount());
            wedding.setBrideAccount(updated.getBrideAccount());
            wedding.setMapUrl(updated.getMapUrl());
            wedding.setTheme(updated.getTheme());
            if (updated.getPhotoUrl() != null) wedding.setPhotoUrl(updated.getPhotoUrl());
            if (updated.getGallery1() != null) wedding.setGallery1(updated.getGallery1());
            if (updated.getGallery2() != null) wedding.setGallery2(updated.getGallery2());
            if (updated.getGallery3() != null) wedding.setGallery3(updated.getGallery3());
            if (updated.getGallery4() != null) wedding.setGallery4(updated.getGallery4());
            if (updated.getGallery5() != null) wedding.setGallery5(updated.getGallery5());
            if (updated.getGallery6() != null) wedding.setGallery6(updated.getGallery6());
            if (updated.getGallery7() != null) wedding.setGallery7(updated.getGallery7());
            if (updated.getGallery8() != null) wedding.setGallery8(updated.getGallery8());
            weddingRepository.save(wedding);
            return ResponseEntity.ok(wedding);
        }).orElse(ResponseEntity.notFound().build());
    }

    // 청첩장 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        guestbookRepository.deleteAll(guestbookRepository.findByWeddingId(id));
        weddingRepository.deleteById(id);
        return ResponseEntity.ok("삭제 완료!");
    }
}