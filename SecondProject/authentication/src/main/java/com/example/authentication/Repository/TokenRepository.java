package com.example.authentication.Repository;

import com.example.authentication.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByIdtoken(Long idtoken); 
}
