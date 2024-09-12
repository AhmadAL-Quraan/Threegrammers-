package com.example.authentication.UserInfo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.authentication.Service.UserDetailsCustom;

@RestController
@RequestMapping("/api/v1/user")
public class UserInfo {

    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(Authentication authentication) {
        // Extract user details from the Authentication object ---> If 
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }


        /*
        Note for me : according to the documentation 
        What getPrincipal() Does:

    Retrieve User Details: The getPrincipal() method returns the principal object,
     which is typically an instance of a class that implements UserDetails (or a custom implementation). 
     This object contains information about the authenticated user, such as username, password, 
     and possibly additional details like roles and authorities.
     */

                                 //I need to add cast
        UserDetailsCustom custom =  (UserDetailsCustom) authentication.getPrincipal();
        String username = custom.getUsername();
        String email = custom.getEmail(); 

        // create a response object 
        Map<String, String> mp = new HashMap<>();
        mp.put("username", username);
        mp.put("email", email);
//mp for map :)
        return ResponseEntity.ok(mp);
    }
}
