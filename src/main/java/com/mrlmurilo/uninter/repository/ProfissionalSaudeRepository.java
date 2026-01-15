package com.mrlmurilo.uninter.repository;

import com.mrlmurilo.uninter.domain.profissional.ProfissionalSaude;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfissionalSaudeRepository extends JpaRepository<ProfissionalSaude, Long> {

    Optional<ProfissionalSaude> findByRegistroProfissional(String registroProfissional);
}
