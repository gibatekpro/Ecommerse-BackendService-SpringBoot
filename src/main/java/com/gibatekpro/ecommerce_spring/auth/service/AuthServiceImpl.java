package com.gibatekpro.ecommerce_spring.auth.service;

import com.gibatekpro.ecommerce_spring.auth.entity.User;
import com.gibatekpro.ecommerce_spring.auth.jwt.service.JwtService;
import com.gibatekpro.ecommerce_spring.auth.dto.AuthResponse;
import com.gibatekpro.ecommerce_spring.auth.dto.AuthRequestBody;
import com.gibatekpro.ecommerce_spring.auth.dto.RegisterRequestBody;
import com.gibatekpro.ecommerce_spring.auth.repo.UserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepo userRepo;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(
            PasswordEncoder passwordEncoder,
            UserRepo userRepo,
            JwtService jwtService,
            AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponse register(RegisterRequestBody requestBody) {

        User user = User.builder()
                .firstName(requestBody.getFirstName())
                .lastName(requestBody.getLastName())
                .email(requestBody.getEmail())
                .password(passwordEncoder.encode(requestBody.getPassword()))
                .build();

        userRepo.save(user);


//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        user.getEmail(),
//                        user.getPassword()
//                )
//        );

        User authenticatedUser = userRepo.findUserByEmail(user.getEmail())
                .orElseThrow();

        return AuthResponse.builder()
                .id(authenticatedUser.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .jwtToken(jwtService.generateToken(user))
                .build();
    }

    @Override
    public AuthResponse authenticate(AuthRequestBody requestBody) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestBody.getEmail(),
                        requestBody.getPassword()
                )
        );

        User user = userRepo.findUserByEmail(requestBody.getEmail())
                .orElseThrow();

        return AuthResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .jwtToken(jwtService.generateToken(user))
                .build();

    }
}
