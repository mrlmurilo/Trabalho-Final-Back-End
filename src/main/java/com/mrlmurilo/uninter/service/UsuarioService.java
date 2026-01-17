package com.mrlmurilo.uninter.service;

import com.mrlmurilo.uninter.dto.auth.CriarUsuarioRequest;
import com.mrlmurilo.uninter.repository.UsuarioRepository;
import com.mrlmurilo.uninter.security.model.Role;
import com.mrlmurilo.uninter.security.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void criar(CriarUsuarioRequest request) {

        if (repository.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("Usuário já existe");
        }

        Usuario usuario = Usuario.builder()
                .email(request.email())
                .senha(passwordEncoder.encode(request.senha()))
                .role(Role.valueOf(request.role()))
                .build();

        repository.save(usuario);
    }
}
