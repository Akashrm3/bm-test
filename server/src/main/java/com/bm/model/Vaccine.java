package com.bm.model;

import com.bm.model.audit.Audit;
import com.bm.model.audit.AuditListener;
import com.bm.model.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Setter
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@EntityListeners(AuditListener.class)
public class Vaccine extends AbstractEntity implements Auditable {
    @Embedded
    private Audit audit;

    @Column
    private String vaccineName;

    @Column
    private String barcode;

    @Column
    private String manufacturedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branchId")
    @JsonIgnore
    private Branch branch;
}