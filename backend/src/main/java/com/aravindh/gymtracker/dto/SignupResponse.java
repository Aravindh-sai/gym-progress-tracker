package com.aravindh.gymtracker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupResponse {
    private boolean success;
    private String message;
    private String token;
}
