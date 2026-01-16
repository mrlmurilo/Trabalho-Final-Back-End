package com.mrlmurilo.uninter.controller;

import com.mrlmurilo.uninter.dto.prontuario.CriarProntuarioRequest;
import com.mrlmurilo.uninter.dto.prontuario.ProntuarioResponse;
import com.mrlmurilo.uninter.service.ProntuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prontuarios")
@RequiredArgsConstructor
public class ProntuarioController {

    private final ProntuarioService prontuarioService;

    @PostMapping
    public ProntuarioResponse criar(@RequestBody CriarProntuarioRequest request) {
        return prontuarioService.criar(request);
    }

    @GetMapping("/consulta/{consultaId}")
    public ProntuarioResponse buscarPorConsulta(@PathVariable Long consultaId) {
        return prontuarioService.buscarPorConsulta(consultaId);
    }
}
