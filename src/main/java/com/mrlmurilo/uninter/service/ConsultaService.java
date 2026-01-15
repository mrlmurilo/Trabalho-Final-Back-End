package com.mrlmurilo.uninter.service;

import com.mrlmurilo.uninter.domain.consulta.Consulta;
import com.mrlmurilo.uninter.domain.consulta.StatusConsulta;
import com.mrlmurilo.uninter.domain.paciente.Paciente;
import com.mrlmurilo.uninter.domain.profissional.ProfissionalSaude;
import com.mrlmurilo.uninter.dto.consulta.ConsultaResponse;
import com.mrlmurilo.uninter.dto.consulta.CriarConsultaRequest;
import com.mrlmurilo.uninter.repository.ConsultaRepository;
import com.mrlmurilo.uninter.repository.PacienteRepository;
import com.mrlmurilo.uninter.repository.ProfissionalSaudeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final ProfissionalSaudeRepository profissionalRepository;

    public ConsultaResponse criar(CriarConsultaRequest request) {
        Consulta consulta = criarConsultaInterno(request);
        return toResponse(consulta);
    }

    public List<ConsultaResponse> listar() {
        return consultaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }


    public ConsultaResponse buscarPorId(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        return new ConsultaResponse(
                consulta.getId(),
                consulta.getPaciente().getId(),
                consulta.getPaciente().getNome(),
                consulta.getProfissional().getId(),
                consulta.getProfissional().getNome(),
                consulta.getDataHora(),
                consulta.getTipo(),
                consulta.getStatus()
        );

    }

    private ConsultaResponse toResponse(Consulta consulta) {
        return new ConsultaResponse(
                consulta.getId(),
                consulta.getPaciente().getId(),
                consulta.getPaciente().getNome(),
                consulta.getProfissional().getId(),
                consulta.getProfissional().getNome(),
                consulta.getDataHora(),
                consulta.getTipo(),
                consulta.getStatus()
        );
    }

    private Consulta criarConsultaInterno(CriarConsultaRequest request) {

        Paciente paciente = pacienteRepository.findById(request.pacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        ProfissionalSaude profissional = profissionalRepository.findById(request.profissionalId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        Consulta consulta = Consulta.builder()
                .paciente(paciente)
                .profissional(profissional)
                .dataHora(request.dataHora())
                .tipo(request.tipo())
                .status(StatusConsulta.AGENDADA)
                .build();

        return consultaRepository.save(consulta);
    }


}
