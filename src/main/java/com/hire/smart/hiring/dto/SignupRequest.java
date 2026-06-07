package com.hire.smart.hiring.dto;

import lombok.Data;

@Data
public class SignupRequest {
    private String name;
    private String email;
    private String password;
    private String role; // RECRUITER or CANDIDATE
}