package com.mrlmurilo.uninter.service;

import com.mrlmurilo.uninter.domain.audit.AuditAction;
import com.mrlmurilo.uninter.domain.audit.AuditLog;
import com.mrlmurilo.uninter.repository.AuditLogRepository;
import com.mrlmurilo.uninter.security.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuditLogService {

    private final AuditLogRepository repository;

    public void registrar(
            Usuario usuario,
            AuditAction action,
            String detalhe
    ) {
        AuditLog log = AuditLog.builder()
                .usuario(usuario)
                .action(action)
                .detalhe(detalhe)
                .dataHora(LocalDateTime.now())
                .build();

        repository.save(log);
    }
}
