package com.bm.model.audit;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
public class Audit {

    @Column
    private LocalDateTime lastModifiedOn;

    @Column
    private String lastModifiedBy;

}