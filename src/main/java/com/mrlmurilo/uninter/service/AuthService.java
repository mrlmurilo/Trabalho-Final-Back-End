package com.mrlmurilo.uninter.service;

import com.mrlmurilo.uninter.dto.auth.LoginRequest;
import com.mrlmurilo.uninter.dto.auth.LoginResponse;
import com.mrlmurilo.uninter.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) {

        try {

            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );
            UserDetails user = (UserDetails) authentication.getPrincipal();
            String token = jwtService.gerarToken(user);
            return new LoginResponse(token);
        } catch (AuthenticationException ex) {
            throw new RuntimeException("Email ou senha inválidos");
        }


    }
}
