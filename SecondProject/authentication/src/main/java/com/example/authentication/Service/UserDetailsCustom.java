package com.example.authentication.Service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



/*
 I can't just use the email attribute alone i need to initialize them all (email,pass,username,grantedAuthority) :?)
 */
public class UserDetailsCustom implements UserDetails {
    private String password;
    private String username;
    private Collection<? extends GrantedAuthority> auth; 
    public UserDetailsCustom(){

    }
    public UserDetailsCustom(String password,Collection<? extends GrantedAuthority> auth,String username,String id){
        this.password=password;
        this.auth=auth;
        this.username=username;
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
