package com.mrlmurilo.uninter.controller;

import com.mrlmurilo.uninter.domain.paciente.Paciente;
import com.mrlmurilo.uninter.dto.paciente.CriarPacienteRequest;
import com.mrlmurilo.uninter.dto.paciente.PacienteResponse;
import com.mrlmurilo.uninter.repository.PacienteRepository;
import com.mrlmurilo.uninter.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @PostMapping
    public PacienteResponse criar(@RequestBody CriarPacienteRequest request) {
        return pacienteService.criar(request);
    }

    @GetMapping
    public List<PacienteResponse> listar() {
        return pacienteService.listar();
    }

    @GetMapping("/{id}")
    public PacienteResponse buscarPorId(@PathVariable Long id) {
        return pacienteService.buscarPorId(id);
    }
}
