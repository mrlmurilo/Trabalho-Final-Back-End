package com.mrlmurilo.uninter.service;

import com.mrlmurilo.uninter.domain.agenda.Agenda;
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
    private final AgendaService agendaService;

    public ConsultaResponse criar(CriarConsultaRequest request) {

        Paciente paciente = pacienteRepository.findById(request.pacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        // 🔹 Busca agenda disponível
        Agenda agenda = agendaService.buscarHorarioDisponivel(request.agendaId());

        Consulta consulta = Consulta.builder()
                .paciente(paciente)
                .profissional(agenda.getProfissional())
                .agenda(agenda)
                .tipo(request.tipo())
                .status(StatusConsulta.AGENDADA)
                .build();

        consultaRepository.save(consulta);

        // 🔹 Ocupa o horário
        agendaService.ocuparHorario(agenda);

        return toResponse(consulta);
    }

    public List<ConsultaResponse> listar() {
        return consultaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private ConsultaResponse toResponse(Consulta consulta) {
        return new ConsultaResponse(
                consulta.getId(),
                consulta.getPaciente().getId(),
                consulta.getPaciente().getNome(),
                consulta.getProfissional().getId(),
                consulta.getProfissional().getNome(),
                consulta.getAgenda().getData(),
                consulta.getAgenda().getHora(),
                consulta.getTipo(),
                consulta.getStatus()
        );
    }
}
