package com.gibatekpro.ecommerce_spring.auth.jwt.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface JwtService {


    String generateToken(
            Map<String, Object> extractClaims,
            UserDetails userDetails
    );

    //Generate token without extra claims
    String generateToken(UserDetails userDetails);

    Date extractExpiration(String token);

    String extractUsername(String jwtToken);

    <T> T extractClaim(String jwtToken, Function<Claims, T> claimResolver);

    Claims extractAllClaims(String jwtToken);

    Key getSigningKey();

    boolean isTokenExpired(String jwtToken);

    boolean isTokenValid(String jwtToken, UserDetails userDetails);

}
