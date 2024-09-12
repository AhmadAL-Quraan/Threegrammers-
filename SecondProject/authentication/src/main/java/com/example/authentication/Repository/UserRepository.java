package com.example.authentication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.authentication.model.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
  public User findByUsername(String username);
   Optional<User> findByEmail(String email);
}
