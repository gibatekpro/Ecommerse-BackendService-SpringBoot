package com.gibatekpro.ecommerce_spring.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestBody {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

}
