package com.mrlmurilo.uninter.dto.paciente;


import java.time.LocalDate;

public record PacienteResponse(
        Long id,
        String nome,
        String cpf,
        String email,
        String telefone,
        LocalDate dataNascimento
) {}