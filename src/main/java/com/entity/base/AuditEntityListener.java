package com.entity.base;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

public class AuditEntityListener {

    @PrePersist
    public void prePersist(final Auditable entity) {
        entity.setTimeCreated(new Date());
        entity.setTimeUpdated(new Date());
        entity.setCreatedBy("SYSTEM");
        entity.setLastModifiedBy("SYSTEM");
    }

    @PreUpdate
    public void preUpdate(final Auditable entity) {
        entity.setTimeUpdated(new Date());
        entity.setLastModifiedBy("SYSTEM");
    }
}
