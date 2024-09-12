package com.example.authentication.InputOutput;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginAttributeOuput {
    private String username;
    private String email;
    private String accessToken;
    private String refreshToken;
}
