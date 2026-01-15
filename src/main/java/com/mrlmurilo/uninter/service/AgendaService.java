package com.mrlmurilo.uninter.service;

import com.mrlmurilo.uninter.domain.agenda.Agenda;
import com.mrlmurilo.uninter.domain.agenda.StatusAgenda;
import com.mrlmurilo.uninter.domain.profissional.ProfissionalSaude;
import com.mrlmurilo.uninter.dto.agenda.AgendaResponse;
import com.mrlmurilo.uninter.dto.agenda.CriarAgendaRequest;
import com.mrlmurilo.uninter.repository.AgendaRepository;
import com.mrlmurilo.uninter.repository.ProfissionalSaudeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendaService {

    private final AgendaRepository agendaRepository;
    private final ProfissionalSaudeRepository profissionalRepository;

    // 🔹 Criar horário disponível
    public AgendaResponse criar(CriarAgendaRequest request) {

        ProfissionalSaude profissional = profissionalRepository.findById(request.profissionalId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        boolean horarioExiste = agendaRepository
                .findByProfissionalAndData(profissional, request.data())
                .stream()
                .anyMatch(a -> a.getHora().equals(request.hora()));

        if (horarioExiste) {
            throw new RuntimeException("Horário já cadastrado para esse profissional");
        }

        Agenda agenda = Agenda.builder()
                .profissional(profissional)
                .data(request.data())
                .hora(request.hora())
                .status(StatusAgenda.DISPONIVEL)
                .build();

        agendaRepository.save(agenda);

        return toResponse(agenda);
    }

    // 🔹 Listar horários de um profissional
    public List<AgendaResponse> listarPorProfissional(Long profissionalId) {

        ProfissionalSaude profissional = profissionalRepository.findById(profissionalId)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        return agendaRepository.findByProfissional(profissional)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // 🔹 Buscar horário disponível
    public Agenda buscarHorarioDisponivel(Long agendaId) {

        Agenda agenda = agendaRepository.findById(agendaId)
                .orElseThrow(() -> new RuntimeException("Agenda não encontrada"));

        if (agenda.getStatus() != StatusAgenda.DISPONIVEL) {
            throw new RuntimeException("Horário não disponível");
        }

        return agenda;
    }

    // 🔹 Ocupar horário (chamado pela ConsultaService)
    public void ocuparHorario(Agenda agenda) {
        agenda.setStatus(StatusAgenda.OCUPADO);
        agendaRepository.save(agenda);
    }

    private AgendaResponse toResponse(Agenda agenda) {
        return new AgendaResponse(
                agenda.getId(),
                agenda.getProfissional().getId(),
                agenda.getProfissional().getNome(),
                agenda.getData(),
                agenda.getHora(),
                agenda.getStatus()
        );
    }
}
