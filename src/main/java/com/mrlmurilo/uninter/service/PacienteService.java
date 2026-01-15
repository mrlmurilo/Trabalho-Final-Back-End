package com.mrlmurilo.uninter.service;

import com.mrlmurilo.uninter.domain.paciente.Paciente;
import com.mrlmurilo.uninter.dto.paciente.CriarPacienteRequest;
import com.mrlmurilo.uninter.dto.paciente.PacienteResponse;
import com.mrlmurilo.uninter.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteResponse criar(CriarPacienteRequest request) {
        Paciente paciente = Paciente.builder()
                .nome(request.nome())
                .cpf(request.cpf())
                .email(request.email())
                .telefone(request.telefone())
                .build();

        paciente = pacienteRepository.save(paciente);
        return toResponse(paciente);
    }

    public List<PacienteResponse> listar() {
        return pacienteRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public PacienteResponse buscarPorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        return toResponse(paciente);
    }

    private PacienteResponse toResponse(Paciente paciente) {
        return new PacienteResponse(
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getEmail(),
                paciente.getTelefone()
        );
    }
}
