package com.contractmanagement.system;

import com.contractmanagement.system.AuditLog;
import com.contractmanagement.system.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    @Autowired
    public AuditService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public void logAction(String action, String entity, Long entityId) {
        AuditLog auditLog = new AuditLog(action, entity, entityId, LocalDateTime.now());
        auditLogRepository.save(auditLog);
    }
}
