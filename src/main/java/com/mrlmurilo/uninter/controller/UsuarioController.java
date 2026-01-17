package com.mrlmurilo.uninter.controller;

import com.mrlmurilo.uninter.dto.auth.CriarUsuarioRequest;
import com.mrlmurilo.uninter.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public void criar(@RequestBody CriarUsuarioRequest request) {
        usuarioService.criar(request);
    }
}
