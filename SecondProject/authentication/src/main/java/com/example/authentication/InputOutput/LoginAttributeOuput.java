package com.example.authentication.InputOutput;

import lombok.Data;

@Data
public class LoginAttributeOuput {
    private String username;
    private String email;
    private String accessToken;
    private String refreshToken;
}
