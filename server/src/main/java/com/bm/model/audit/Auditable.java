package com.bm.model.audit;

public interface Auditable {

    Audit getAudit();

    void setAudit(Audit audit);
}