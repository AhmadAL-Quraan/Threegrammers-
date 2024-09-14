package com.example.authentication.Controller;

import com.example.authentication.Service.AuthService;
import com.example.authentication.Service.UserDetailServiceCustom;
import com.example.authentication.Service.UserService;
import com.example.authentication.Security.JWTUtility;
import com.example.authentication.model.User;
import com.example.authentication.model.Token;
import com.example.authentication.InputOutput.LoginAttributeInput;
import com.example.authentication.InputOutput.SignInAttributeInput;
import com.example.authentication.InputOutput.SignInAttributeOutput;
import com.example.authentication.Repository.TokenRepository;
import com.example.authentication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class authController {

  private AuthService authnticationService;
  private JWTUtility jwtUtility;
  private UserDetailServiceCustom userDetailServiceCustom;
  
  @Autowired
  public authController(AuthService authService,JWTUtility jwtUtility,UserDetailServiceCustom userDetailServiceCustom){
    this.authnticationService=authService;
    this.userDetailServiceCustom=userDetailServiceCustom;
    this.jwtUtility=jwtUtility;
  }

  @PostMapping("/signup")
  public ResponseEntity<?> signup(@RequestBody SignInAttributeInput sign) {
    List<String> signed=authnticationService.signup(sign.getUsername(),sign.getEmail(),sign.getPassword());
    return new ResponseEntity<>(signed,HttpStatus.CREATED);
  }
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginAttributeInput log) {
      return authnticationService.login(log.getEmail(), log.getPassword());
  } 
  @PostMapping("/refreshToken")
  public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
      String refreshToken = request.get("refreshToken");
      if (refreshToken == null || refreshToken.isEmpty()) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                  .body("Refresh token is required.");
      }
//checkkkk
      if (jwtUtility.isTokenExpired(refreshToken)) {
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                  .body("Refresh token is expired.");
      }
      String username = jwtUtility.extractEmail(refreshToken);
// Generate new access token
      String newAccessToken = jwtUtility.generateAccessToken(username);

    // Return it
      Map<String, String> response = new HashMap<>();
      response.put("accessToken", newAccessToken);

      return ResponseEntity.ok(response);
  }
}
