package com.mrlmurilo.uninter.repository;

import com.mrlmurilo.uninter.domain.agenda.Agenda;
import com.mrlmurilo.uninter.domain.profissional.ProfissionalSaude;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    List<Agenda> findByProfissional(ProfissionalSaude profissional);

    List<Agenda> findByProfissionalAndData(ProfissionalSaude profissional, LocalDate data);
}
