package com.example.authentication.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.authentication.InputOutput.SignInAttributeInput;
import com.example.authentication.Repository.UserRepository;
import com.example.authentication.model.User;

@Service
public class UserService  {
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public User createUser(String password, String email, String username) {
        User user = new User(username, email, password);
        return userRepository.save(user);
    }

    
 
}
