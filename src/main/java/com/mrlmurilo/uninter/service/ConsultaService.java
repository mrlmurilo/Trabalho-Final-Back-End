package com.mrlmurilo.uninter.service;

import com.mrlmurilo.uninter.domain.agenda.Agenda;
import com.mrlmurilo.uninter.domain.audit.AuditAction;
import com.mrlmurilo.uninter.domain.consulta.Consulta;
import com.mrlmurilo.uninter.domain.consulta.StatusConsulta;
import com.mrlmurilo.uninter.domain.paciente.Paciente;
import com.mrlmurilo.uninter.domain.profissional.ProfissionalSaude;
import com.mrlmurilo.uninter.dto.consulta.ConsultaResponse;
import com.mrlmurilo.uninter.dto.consulta.CriarConsultaRequest;
import com.mrlmurilo.uninter.repository.ConsultaRepository;
import com.mrlmurilo.uninter.repository.PacienteRepository;
import com.mrlmurilo.uninter.repository.ProfissionalSaudeRepository;
import com.mrlmurilo.uninter.security.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final AgendaService agendaService;
    private final AuditLogService auditLogService;

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

        Usuario usuario = getUsuarioLogado();

        auditLogService.registrar(usuario, AuditAction.CRIAR_CONSULTA,
                "Consulta criada para paciente ID " + paciente.getId()
                        + " com profissional ID " + agenda.getProfissional().getId()
                        + " em " + agenda.getData() + " " + agenda.getHora()
        );

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

    public ConsultaResponse cancelar(Long consultaId) {

        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        if (consulta.getStatus() == StatusConsulta.CANCELADA) {
            throw new RuntimeException("Consulta já está cancelada");
        }

        // 🔹 Cancela consulta
        consulta.setStatus(StatusConsulta.CANCELADA);

        // 🔹 Libera agenda
        agendaService.liberarHorario(consulta.getAgenda());

        consultaRepository.save(consulta);

        Usuario usuario = getUsuarioLogado();

        auditLogService.registrar(
                usuario,
                AuditAction.CANCELAR_CONSULTA,
                "Consulta ID " + consulta.getId() + " cancelada"
        );

        return toResponse(consulta);
    }

    private Usuario getUsuarioLogado() {
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal instanceof Usuario usuario) {
            return usuario;
        }

        throw new RuntimeException("Usuário não autenticado");
    }

    public ConsultaResponse finalizar(Long consultaId) {

        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        if (consulta.getStatus() == StatusConsulta.CANCELADA) {
            throw new RuntimeException("Consulta cancelada não pode ser finalizada");
        }

        if (consulta.getStatus() == StatusConsulta.FINALIZADA) {
            throw new RuntimeException("Consulta já está finalizada");
        }

        // 🔹 Finaliza consulta
        consulta.setStatus(StatusConsulta.FINALIZADA);
        consultaRepository.save(consulta);

        Usuario usuario = (Usuario) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        auditLogService.registrar(
                usuario,
                AuditAction.FINALIZAR_CONSULTA,
                "Consulta ID " + consulta.getId() + " finalizada"
        );

        return toResponse(consulta);
    }




}
