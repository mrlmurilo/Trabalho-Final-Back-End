package com.mrlmurilo.uninter.service;

import com.mrlmurilo.uninter.domain.audit.AuditAction;
import com.mrlmurilo.uninter.domain.consulta.Consulta;
import com.mrlmurilo.uninter.domain.consulta.StatusConsulta;
import com.mrlmurilo.uninter.domain.prontuario.Prontuario;
import com.mrlmurilo.uninter.dto.prontuario.CriarProntuarioRequest;
import com.mrlmurilo.uninter.dto.prontuario.ProntuarioResponse;
import com.mrlmurilo.uninter.repository.ConsultaRepository;
import com.mrlmurilo.uninter.repository.ProntuarioRepository;
import com.mrlmurilo.uninter.security.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProntuarioService {

    private final ProntuarioRepository prontuarioRepository;
    private final ConsultaRepository consultaRepository;
    private final AuditLogService auditLogService;

    public ProntuarioResponse criar(CriarProntuarioRequest request) {

        Consulta consulta = consultaRepository.findById(request.consultaId())
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        if (consulta.getStatus() != StatusConsulta.FINALIZADA) {
            throw new RuntimeException("Prontuário só pode ser criado para consulta FINALIZADA");
        }

        if (prontuarioRepository.existsByConsultaId(request.consultaId())) {
            throw new RuntimeException("Consulta já possui prontuário");
        }

        Prontuario prontuario = Prontuario.builder()
                .consulta(consulta)
                .descricao(request.descricao())
                .prescricao(request.prescricao())
                .dataRegistro(LocalDateTime.now())
                .build();

        prontuarioRepository.save(prontuario);

        Usuario usuario = (Usuario) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        auditLogService.registrar(
                usuario,
                AuditAction.CRIAR_PRONTUARIO,
                "Prontuário criado para consulta ID " + consulta.getId()
        );

        return toResponse(prontuario);
    }

    private ProntuarioResponse toResponse(Prontuario p) {
        return new ProntuarioResponse(
                p.getId(),
                p.getConsulta().getId(),
                p.getConsulta().getPaciente().getId(),
                p.getConsulta().getPaciente().getNome(),
                p.getConsulta().getProfissional().getId(),
                p.getConsulta().getProfissional().getNome(),
                p.getDescricao(),
                p.getPrescricao(),
                p.getDataRegistro()
        );
    }

    public ProntuarioResponse buscarPorConsulta(Long consultaId) {

        Prontuario prontuario = prontuarioRepository.findByConsultaId(consultaId)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado para esta consulta"));

        return toResponse(prontuario);
    }

}



