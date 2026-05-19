package com.example.wedding.domain.guestbook;

import com.example.wedding.domain.wedding.WeddingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guestbooks")
@RequiredArgsConstructor
public class GuestbookController {

    private final GuestbookRepository guestbookRepository;
    private final WeddingRepository weddingRepository;

    // 방명록 작성
    @PostMapping("/{weddingId}")
    public ResponseEntity<?> write(@PathVariable Long weddingId,
                                   @RequestBody Guestbook guestbook) {
        weddingRepository.findById(weddingId).ifPresent(guestbook::setWedding);
        guestbookRepository.save(guestbook);
        return ResponseEntity.ok(guestbook);
    }

    // 방명록 조회
    @GetMapping("/{weddingId}")
    public ResponseEntity<List<Guestbook>> getList(@PathVariable Long weddingId) {
        return ResponseEntity.ok(guestbookRepository.findByWeddingId(weddingId));
    }

    // 방명록 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        guestbookRepository.deleteById(id);
        return ResponseEntity.ok("삭제 완료!");
    }
}