package com.mrlmurilo.uninter.service;

import com.mrlmurilo.uninter.domain.profissional.ProfissionalSaude;
import com.mrlmurilo.uninter.dto.profissional.CriarProfissionalRequest;
import com.mrlmurilo.uninter.dto.profissional.ProfissionalResponse;
import com.mrlmurilo.uninter.repository.ProfissionalSaudeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfissionalSaudeService {

    private final ProfissionalSaudeRepository repository;

    public ProfissionalResponse criar(CriarProfissionalRequest request) {
        ProfissionalSaude profissional = ProfissionalSaude.builder()
                .nome(request.nome())
                .especialidade(request.especialidade())
                .telefone(request.telefone())
                .email(request.email())
                .build();

        return toResponse(repository.save(profissional));
    }

    public List<ProfissionalResponse> listar() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private ProfissionalResponse toResponse(ProfissionalSaude profissional) {
        return new ProfissionalResponse(
                profissional.getId(),
                profissional.getNome(),
                profissional.getEspecialidade(),
                profissional.getRegistroProfissional(),
                profissional.getTelefone(),
                profissional.getEmail()
        );
    }
}
