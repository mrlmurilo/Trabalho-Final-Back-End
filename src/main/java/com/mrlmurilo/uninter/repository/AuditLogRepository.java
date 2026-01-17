package com.mrlmurilo.uninter.repository;

import com.mrlmurilo.uninter.domain.audit.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
