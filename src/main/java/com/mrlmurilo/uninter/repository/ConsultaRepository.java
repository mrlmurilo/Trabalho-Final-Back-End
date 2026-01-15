package com.mrlmurilo.uninter.repository;

import com.mrlmurilo.uninter.domain.consulta.Consulta;
import com.mrlmurilo.uninter.domain.profissional.ProfissionalSaude;
import com.mrlmurilo.uninter.domain.paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByPaciente(Paciente paciente);

    List<Consulta> findByProfissional(ProfissionalSaude profissional);

    boolean existsByProfissionalAndDataHora(ProfissionalSaude profissional, LocalDateTime dataHora);
}
