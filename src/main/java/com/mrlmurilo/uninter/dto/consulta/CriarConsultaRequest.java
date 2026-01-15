package com.mrlmurilo.uninter.dto.consulta;

import com.mrlmurilo.uninter.domain.consulta.TipoConsulta;

import java.time.LocalDateTime;

public record CriarConsultaRequest(
        Long pacienteId,
        Long profissionalId,
        LocalDateTime dataHora,
        TipoConsulta tipo
) {}
