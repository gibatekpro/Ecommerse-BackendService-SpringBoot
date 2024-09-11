package com.gibatekpro.ecommerce_spring.auth.service;

import com.gibatekpro.ecommerce_spring.auth.dto.AuthResponse;
import com.gibatekpro.ecommerce_spring.auth.dto.AuthRequestBody;
import com.gibatekpro.ecommerce_spring.auth.dto.RegisterRequestBody;

public interface AuthService {

    AuthResponse register(RegisterRequestBody requestBody);

    AuthResponse authenticate(AuthRequestBody requestBody);


}
