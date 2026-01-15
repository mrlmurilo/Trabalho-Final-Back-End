package com.mrlmurilo.uninter.controller;

import com.mrlmurilo.uninter.dto.profissional.CriarProfissionalRequest;
import com.mrlmurilo.uninter.dto.profissional.ProfissionalResponse;
import com.mrlmurilo.uninter.service.ProfissionalSaudeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissionais")
@RequiredArgsConstructor
public class ProfissionalSaudeController {

    private final ProfissionalSaudeService service;

    @PostMapping
    public ProfissionalResponse criar(@RequestBody CriarProfissionalRequest request) {
        return service.criar(request);
    }

    @GetMapping
    public List<ProfissionalResponse> listar() {
        return service.listar();
    }
}
