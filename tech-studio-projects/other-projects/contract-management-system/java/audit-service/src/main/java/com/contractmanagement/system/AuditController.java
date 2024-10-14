package com.contractmanagement.system;

import com.contractmanagement.system.AuditLog;
import com.contractmanagement.system.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/audits")
public class AuditController {

    private final AuditLogRepository auditLogRepository;

    @Autowired
    public AuditController(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @GetMapping
    public List<AuditLog> getAllAuditLogs() {
        return auditLogRepository.findAll();
    }

    @GetMapping("/{id}")
    public AuditLog getAuditLog(@PathVariable Long id) {
        return auditLogRepository.findById(id).orElse(null);
    }

    @PostMapping
    public void createAuditLog(@RequestBody AuditLog auditLog) {
        auditLogRepository.save(auditLog);
    }
}
}
