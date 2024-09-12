package com.example.authentication.InputOutput;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor

@Data
public class SignInAttributeInput {
    private String username;
    private String email;
    private String password;
}
