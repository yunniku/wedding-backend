package com.example.wedding.domain.guestbook;

import com.example.wedding.domain.wedding.Wedding;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "guestbooks")
@Getter
@Setter
@NoArgsConstructor
public class Guestbook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String guestName;   // 방문자 이름

    @Column(nullable = false)
    private String message;     // 축하 메시지

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "wedding_id")
    private Wedding wedding;

}