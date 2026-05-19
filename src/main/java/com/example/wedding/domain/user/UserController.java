package com.example.wedding.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("이미 사용중인 이메일입니다.");
        }
        userRepository.save(user);
        return ResponseEntity.ok("회원가입 성공!");
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User found = userRepository.findByEmail(user.getEmail());
        if (found == null || !found.getPassword().equals(user.getPassword())) {
            return ResponseEntity.badRequest().body("이메일 또는 비밀번호가 틀렸습니다.");
        }
        return ResponseEntity.ok("로그인 성공!");
    }
}