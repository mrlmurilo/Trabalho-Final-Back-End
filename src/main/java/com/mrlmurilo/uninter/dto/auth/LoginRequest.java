package com.mrlmurilo.uninter.dto.auth;

public record LoginRequest(
        String email,
        String password
) {}
