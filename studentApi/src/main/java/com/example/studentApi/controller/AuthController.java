package com.example.studentApi.controller;

import com.example.studentApi.dto.LoginRequestDto;
import com.example.studentApi.dto.LoginResponseDto;
import com.example.studentApi.dto.RegisterRequestDto;
import com.example.studentApi.dto.RegisterResponseDto;
import com.example.studentApi.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;
    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@Valid @RequestBody RegisterRequestDto dto){
       return ResponseEntity.status(201).body(service.register(dto));
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(service.login(dto));
    }
}
