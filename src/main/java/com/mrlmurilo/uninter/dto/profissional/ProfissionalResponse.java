package com.mrlmurilo.uninter.dto.profissional;

import com.mrlmurilo.uninter.domain.profissional.Especialidade;

public record ProfissionalResponse(
        Long id,
        String nome,
        Especialidade especialidade,
        String registroProfissional,
        String telefone,
        String email
) {
}
