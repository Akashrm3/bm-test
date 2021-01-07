package com.bm.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity implements Serializable {
    protected static final int DEFAULT_SCALE = 2;
    private static final long serialVersionUID = -3733796182675284472L;
    /**
     * Primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vmsSeqGen")
    @SequenceGenerator(name = "vmsSeqGen", sequenceName = "vmsSeq", initialValue = 1, allocationSize = 100)
    public long id;
    /**
     * Auto-incrementing version (integer) column.  Incremented automatically
     * on each UPDATE.  Can be used for optimistic locking.
     */
    @Version
    int version;

}
