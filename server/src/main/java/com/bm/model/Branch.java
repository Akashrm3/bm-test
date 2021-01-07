package com.bm.model;

import com.bm.model.audit.Audit;
import com.bm.model.audit.AuditListener;
import com.bm.model.audit.Auditable;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditListener.class)
public class Branch extends AbstractEntity implements Auditable {

    @Embedded
    private Audit audit;

    @Column
    private String branchName;

    @Column
    private String address;

    @Column
    private String contactNumber;

    @Column
    private String emailAddress;

    @Column
    private String branchManager;

    @Column
    private int slotAvailable;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "branch", orphanRemoval = true)
    private Set<Vaccine> vaccine;

}