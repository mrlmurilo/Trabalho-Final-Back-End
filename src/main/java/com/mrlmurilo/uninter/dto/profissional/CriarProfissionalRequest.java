package com.mrlmurilo.uninter.dto.profissional;

import com.mrlmurilo.uninter.domain.profissional.Especialidade;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record CriarProfissionalRequest(
        String nome,
        @Enumerated(EnumType.STRING)
        Especialidade especialidade,
        String registroProfissional,
        String telefone,
        String email
) {
}
