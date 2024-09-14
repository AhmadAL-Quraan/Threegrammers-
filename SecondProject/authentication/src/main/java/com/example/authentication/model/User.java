package com.example.authentication.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Collection;
@Entity
@Table(name="User_App")
@Data
@RequiredArgsConstructor
public class User { 
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
    @Column(nullable = false,unique = true,name = "username")
    private String username;

    @Column(nullable = false,unique = true,name = "email")
    private String email;


    @Column(nullable = false)
    private String password;

    public User(String username,String email,String password){
        this.email=email;
        this.password=password;
        this.username=username;
    }


   private String role;

    //give authority 
    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities(){
    //     return null;
    // }

    // @Override
    // public boolean isAccountNonExpired() {
    //     return true;
    // }

    // @Override
    // public boolean isAccountNonLocked() {
    //     return true;
    // }

    // @Override
    // public boolean isCredentialsNonExpired() {
    //     return true;
    // }

    // @Override
    // public boolean isEnabled() {
    //     return true;
    // }

   
    
}
