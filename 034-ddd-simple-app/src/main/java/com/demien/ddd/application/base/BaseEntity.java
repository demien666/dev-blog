package com.demien.ddd.application.base;

import com.demien.ddd.application.EventBus;
import com.demien.ddd.application.SpringApplicationContext;

public abstract class BaseEntity {

    static long maxId = 0;

    private Long id;

    public BaseEntity() {
        this.id = maxId++;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventBus getEventBus() {
        return (EventBus) SpringApplicationContext.getBean(EventBus.class);
    }
}
