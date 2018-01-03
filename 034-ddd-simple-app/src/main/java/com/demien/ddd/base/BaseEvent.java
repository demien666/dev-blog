package com.demien.ddd.base;

public class BaseEvent {
    private final Long entityId;

    public BaseEvent(Long entityId) {
        this.entityId = entityId;
    }

    public Long getEntityId() {
        return entityId;
    }
}
