package com.mrlmurilo.uninter.dto.prontuario;

public record CriarProntuarioRequest(
        Long consultaId,
        String descricao,
        String prescricao
) {}

