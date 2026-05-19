package com.example.wedding.domain.wedding;

import com.example.wedding.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "weddings")
@Getter
@Setter
@NoArgsConstructor
public class Wedding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String groomName;

    @Column(nullable = false)
    private String brideName;

    @Column(nullable = false)
    private String weddingDate;

    @Column(nullable = false)
    private String venue;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Column(unique = true)
    private String shareCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "theme")
    private String theme;

    private String groomPhone;
    private String bridePhone;

    @Column(columnDefinition = "TEXT")
    private String groomAccount;

    @Column(columnDefinition = "TEXT")
    private String brideAccount;

    @Column(columnDefinition = "LONGTEXT")
    private String photoUrl;

    @Column(columnDefinition = "TEXT")
    private String venue2;

    @Column(columnDefinition = "TEXT")
    private String mapUrl;

    @Column(columnDefinition = "LONGTEXT")
    private String gallery1;
    @Column(columnDefinition = "LONGTEXT")
    private String gallery2;
    @Column(columnDefinition = "LONGTEXT")
    private String gallery3;
    @Column(columnDefinition = "LONGTEXT")
    private String gallery4;
    @Column(columnDefinition = "LONGTEXT")
    private String gallery5;
    @Column(columnDefinition = "LONGTEXT")
    private String gallery6;
    @Column(columnDefinition = "LONGTEXT")
    private String gallery7;
    @Column(columnDefinition = "LONGTEXT")
    private String gallery8;
}