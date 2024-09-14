package com.example.authentication.UserInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.authentication.Repository.UserRepository;
import com.example.authentication.Security.JWTUtility;
import com.example.authentication.Service.UserDetailServiceCustom;
import com.example.authentication.Service.UserDetailsCustom;
import com.example.authentication.Service.UserService;
import com.example.authentication.model.User;
import com.nimbusds.jose.proc.SecurityContext;

@RestController
@RequestMapping("/api/v1/auth/user")
public class UserInfo {

    private UserService userService;
    private JWTUtility jwtUtility;
    private UserRepository userRepository;
    @Autowired
    public UserInfo(UserService userService,JWTUtility jwtUtility,UserRepository userRepository){
        this.userService = userService;
        this.jwtUtility=jwtUtility;
         this.userRepository=userRepository;
    } 

    @GetMapping("/info")
         public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String token) {

            try {
                // Remove the "Bearer " prefix from the token
                String jwtToken = token.substring(7);
                //Extract email ---> I don't why it extract the email not the username
                String username = jwtUtility.extractEmail(token);
               //Debugging
                // System.out.println(username);

                //Throw email extract from the database the info about the user
                User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Didn't find the user? :( "));
                
                
                //Debugging 
                //System.out.println(user.getUsername());
                 //System.out.println(user.getEmail());
                 //System.out.println(user.getId());

             
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("username", user.getUsername());
                responseMap.put("email", user.getEmail());
                responseMap.put("id", user.getId());
    
                return new ResponseEntity<>(responseMap, HttpStatus.OK);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
            }
    }
}
