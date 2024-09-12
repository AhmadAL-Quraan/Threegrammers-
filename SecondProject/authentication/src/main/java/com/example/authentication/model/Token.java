package com.example.authentication.model;

import java.sql.Timestamp;
import java.time.Instant;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idtoken;

    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private User user;
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Boolean isLoggedIn;
    @Column(nullable = false,updatable = true)
    private Timestamp updatedAt;

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Timestamp.from(Instant.now());
    }
    @PrePersist
    protected void onCreate() {
        createdAt = Timestamp.from(Instant.now());
        updatedAt = createdAt;
        isLoggedIn = true;
    }

}
