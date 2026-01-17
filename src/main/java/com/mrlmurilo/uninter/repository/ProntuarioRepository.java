package com.mrlmurilo.uninter.repository;

import com.mrlmurilo.uninter.domain.consulta.Consulta;
import com.mrlmurilo.uninter.domain.prontuario.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {

    boolean existsByConsultaId(Long consultaId);

    Optional<Prontuario> findByConsultaId(Long consultaId);
}
