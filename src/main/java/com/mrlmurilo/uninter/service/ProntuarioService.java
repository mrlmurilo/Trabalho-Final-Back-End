package com.mrlmurilo.uninter.service;

import com.mrlmurilo.uninter.domain.consulta.Consulta;
import com.mrlmurilo.uninter.domain.consulta.StatusConsulta;
import com.mrlmurilo.uninter.domain.prontuario.Prontuario;
import com.mrlmurilo.uninter.dto.prontuario.CriarProntuarioRequest;
import com.mrlmurilo.uninter.dto.prontuario.ProntuarioResponse;
import com.mrlmurilo.uninter.repository.ConsultaRepository;
import com.mrlmurilo.uninter.repository.ProntuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProntuarioService {

    private final ProntuarioRepository prontuarioRepository;
    private final ConsultaRepository consultaRepository;

    public ProntuarioResponse criar(CriarProntuarioRequest request) {

        Consulta consulta = consultaRepository.findById(request.consultaId())
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        if (consulta.getStatus() != StatusConsulta.AGENDADA) {
            throw new RuntimeException("Consulta não pode gerar prontuário");
        }

        prontuarioRepository.findByConsulta(consulta)
                .ifPresent(p -> {
                    throw new RuntimeException("Prontuário já existe para essa consulta");
                });

        Prontuario prontuario = Prontuario.builder()
                .consulta(consulta)
                .descricao(request.descricao())
                .prescricao(request.prescricao())
                .dataRegistro(LocalDateTime.now())
                .build();

        prontuarioRepository.save(prontuario);

        // 🔹 Finaliza consulta
        consulta.setStatus(StatusConsulta.REALIZADA);
        consultaRepository.save(consulta);

        return toResponse(prontuario);
    }

    public ProntuarioResponse buscarPorConsulta(Long consultaId) {

        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        Prontuario prontuario = prontuarioRepository.findByConsulta(consulta)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado"));

        return toResponse(prontuario);
    }

    private ProntuarioResponse toResponse(Prontuario prontuario) {
        Consulta c = prontuario.getConsulta();

        return new ProntuarioResponse(
                prontuario.getId(),
                c.getId(),
                c.getPaciente().getId(),
                c.getPaciente().getNome(),
                c.getProfissional().getId(),
                c.getProfissional().getNome(),
                prontuario.getDescricao(),
                prontuario.getPrescricao(),
                prontuario.getDataRegistro()
        );
    }
}

