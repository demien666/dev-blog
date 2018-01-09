package com.demien.es.system;

import java.util.concurrent.atomic.AtomicLong;

public abstract class Entity {
    private static AtomicLong idCounter = new AtomicLong(1);
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Entity() {
        this.id = idCounter.incrementAndGet();
    }

    public void exception(String messageText) throws Exception {
        throw new Exception(messageText);

    }


}
