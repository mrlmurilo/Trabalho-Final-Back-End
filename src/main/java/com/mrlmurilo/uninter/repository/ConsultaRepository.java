package com.mrlmurilo.uninter.repository;

import com.mrlmurilo.uninter.domain.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByPacienteId(Long pacienteId);

    List<Consulta> findByProfissionalId(Long profissionalId);

    List<Consulta> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);
}
