package com.demien.ddd.base;

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
}
