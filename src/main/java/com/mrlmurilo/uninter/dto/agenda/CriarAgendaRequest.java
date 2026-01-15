package com.mrlmurilo.uninter.dto.agenda;

import java.time.LocalDate;
import java.time.LocalTime;

public record CriarAgendaRequest(
        Long profissionalId,
        LocalDate data,
        LocalTime hora
) {}
