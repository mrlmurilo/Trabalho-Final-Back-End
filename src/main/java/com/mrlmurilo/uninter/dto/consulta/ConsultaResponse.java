package com.mrlmurilo.uninter.dto.consulta;

import com.mrlmurilo.uninter.domain.consulta.StatusConsulta;
import com.mrlmurilo.uninter.domain.consulta.TipoConsulta;

import java.time.LocalDate;
import java.time.LocalTime;

public record ConsultaResponse(
        Long id,
        Long pacienteId,
        String pacienteNome,
        Long profissionalId,
        String profissionalNome,
        LocalDate data,
        LocalTime hora,
        TipoConsulta tipo,
        StatusConsulta status
) {}
