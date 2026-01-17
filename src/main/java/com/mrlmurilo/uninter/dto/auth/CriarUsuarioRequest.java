package com.mrlmurilo.uninter.dto.auth;

public record CriarUsuarioRequest(
        String email,
        String senha,
        String role
) {}
