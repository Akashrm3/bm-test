package com.bm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Setter
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppointmentDTO {

    @Column
    private String startTime;

    @Column
    private String endTime;

    @Column
    private Date dateOfAppointment;

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
}