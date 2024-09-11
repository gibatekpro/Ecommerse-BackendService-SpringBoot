package com.gibatekpro.ecommerce_spring.auth.controller;

import com.gibatekpro.ecommerce_spring.auth.dto.AuthRequestBody;
import com.gibatekpro.ecommerce_spring.auth.dto.AuthResponse;
import com.gibatekpro.ecommerce_spring.auth.dto.RegisterRequestBody;
import com.gibatekpro.ecommerce_spring.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequestBody requestBody
    ){
        return ResponseEntity.ok(authService.register(requestBody));
    }

    @CrossOrigin(origins = "https://localhost:4200", allowedHeaders = {"*"})
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody AuthRequestBody requestBody
    ){
        return ResponseEntity.ok(authService.authenticate(requestBody));
    }


}
