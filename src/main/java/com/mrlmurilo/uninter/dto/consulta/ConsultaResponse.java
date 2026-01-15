package com.mrlmurilo.uninter.dto.consulta;

import com.mrlmurilo.uninter.domain.consulta.StatusConsulta;
import com.mrlmurilo.uninter.domain.consulta.TipoConsulta;

import java.time.LocalDateTime;

public record ConsultaResponse(
        Long id,
        Long pacienteId,
        String pacienteNome,
        Long profissionalId,
        String profissionalNome,
        LocalDateTime dataHora,
        TipoConsulta tipo,
        StatusConsulta status
) {}
