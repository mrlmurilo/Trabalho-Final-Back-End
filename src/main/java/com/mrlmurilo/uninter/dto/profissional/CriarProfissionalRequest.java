package com.mrlmurilo.uninter.dto.profissional;

import com.mrlmurilo.uninter.domain.profissional.Especialidade;

public record CriarProfissionalRequest(
        String nome,
        Especialidade especialidade,
        String registroProfissional,
        String telefone,
        String email
) {
}
