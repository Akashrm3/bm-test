package com.bm.model.audit;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;

public class AuditListener {

    @PrePersist
    public void runAudit(Auditable auditable) {
        Audit audit = auditable.getAudit();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = "System";
        if (authentication != null) {
            currentPrincipalName = authentication.getName();
        }
        if (audit == null) {
            audit = new Audit();
            auditable.setAudit(audit);
        }

        audit.setLastModifiedOn(LocalDateTime.now());
        audit.setLastModifiedBy(currentPrincipalName);
    }

}