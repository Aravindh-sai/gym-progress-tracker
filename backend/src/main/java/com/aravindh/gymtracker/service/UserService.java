package com.aravindh.gymtracker.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.aravindh.gymtracker.dto.LoginResponse;
import com.aravindh.gymtracker.dto.SignupResponse;
import com.aravindh.gymtracker.entity.User;
import com.aravindh.gymtracker.repository.UserRepository;
import com.aravindh.gymtracker.security.JwtService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserService(
            UserRepository userRepository,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public SignupResponse signup(
            String name,
            String email,
            String password,
            String preferredUnit) {
                SignupResponse response = new SignupResponse();
        if (userRepository.existsByEmail(email)) {
            response.setSuccess(false);
            response.setMessage("User Already Exists");
            return response;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(password);
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPasswordHash(hashedPassword);
        user.setPreferredUnit(preferredUnit);
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        response.setSuccess(true);
        response.setMessage("Signup successful");
        String token = jwtService.generateToken(email);
        response.setToken(token);
        return response;
    }

    public LoginResponse login(String email, String password) {
        User user = userRepository.findByEmail(email);
        LoginResponse response = new LoginResponse();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (user == null) {
            response.setSuccess(false);
            response.setMessage("The user doesn't exist");
            return response;
        }
        String storedHash = user.getPasswordHash();
        if (!encoder.matches(password, storedHash)) {
            response.setSuccess(false);
            response.setMessage("Password incorrect");
            return response;
        }
        response.setSuccess(true);
        response.setMessage("Login successful");
        String token = jwtService.generateToken(email);
        response.setToken(token);
        return response;
    }
}
