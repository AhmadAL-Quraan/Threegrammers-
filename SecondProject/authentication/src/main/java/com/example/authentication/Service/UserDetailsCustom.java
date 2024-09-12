package com.example.authentication.Service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



/*
 I can't just use the email attribute alone i need to initialize them all (email,pass,username,grantedAuthority) :?)
 */
public class UserDetailsCustom implements UserDetails {
    private String email;
    private String password;
    private String username;
    private Collection<? extends GrantedAuthority> auth; 
    public UserDetailsCustom(String email,String password,Collection<? extends GrantedAuthority> auth,String username){
        this.email=email;
        this.password=password;
        this.auth=auth;
        this.username=username;
    }
    public String getEmail(){
        return this.email;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.auth;
    }
    @Override
    public String getPassword() {
        return this.password;
    }
    @Override
    public String getUsername() {
        return this.username;
    }
    
}
