package com.gibatekpro.ecommerce_spring.auth.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthResponse{

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String jwtToken;

}
