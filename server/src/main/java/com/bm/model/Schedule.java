package com.bm.model;

import com.bm.common.ScheduleStatus;
import com.bm.common.VaccinationStatus;
import com.bm.model.audit.Audit;
import com.bm.model.audit.AuditListener;
import com.bm.model.audit.Auditable;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import java.util.Date;

@Setter
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditListener.class)
public class Schedule extends AbstractEntity implements Auditable {
    @Embedded
    private Audit audit;

    @Column
    private String startTime;

    @Column
    private String endTime;

    @Column
    private Date appointmentDate;

    @Column
    private Long branchId;

    @Column
    private String customerName;

    @Column
    private String customerMobile;

    @Column
    private String customerEmail;

    @Column
    private String customerAddress;

    @Column
    private ScheduleStatus status;

    @Column
    private VaccinationStatus vaccinationStatus;

    @Column
    private Long totalAmount;


}