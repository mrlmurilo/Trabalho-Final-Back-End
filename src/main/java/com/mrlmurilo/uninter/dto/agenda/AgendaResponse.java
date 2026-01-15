package com.mrlmurilo.uninter.dto.agenda;

import com.mrlmurilo.uninter.domain.agenda.StatusAgenda;

import java.time.LocalDate;
import java.time.LocalTime;

public record AgendaResponse(
        Long id,
        Long profissionalId,
        String profissionalNome,
        LocalDate data,
        LocalTime hora,
        StatusAgenda status
) {}
