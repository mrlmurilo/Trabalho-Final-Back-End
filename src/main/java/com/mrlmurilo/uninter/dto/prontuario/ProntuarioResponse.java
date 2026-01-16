package com.mrlmurilo.uninter.dto.prontuario;

import java.time.LocalDateTime;

public record ProntuarioResponse(
        Long id,
        Long consultaId,
        Long pacienteId,
        String pacienteNome,
        Long profissionalId,
        String profissionalNome,
        String descricao,
        String prescricao,
        LocalDateTime dataRegistro
) {}
