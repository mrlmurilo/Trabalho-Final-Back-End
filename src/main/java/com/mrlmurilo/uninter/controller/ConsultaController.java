package com.mrlmurilo.uninter.controller;

import com.mrlmurilo.uninter.dto.consulta.ConsultaResponse;
import com.mrlmurilo.uninter.dto.consulta.CriarConsultaRequest;
import com.mrlmurilo.uninter.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    @PostMapping
    public ConsultaResponse criar(@RequestBody CriarConsultaRequest request) {
        return consultaService.criar(request);
    }

    @GetMapping
    public List<ConsultaResponse> listar() {
        return consultaService.listar();
    }
}
