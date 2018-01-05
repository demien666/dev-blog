package com.demien.es.domain;

public class VOChangeEvent<T> implements Event {

    private final String GUID;
    private final T vo;

    public VOChangeEvent(String guid, T vo) {
        this.GUID = guid;
        this.vo = vo;
    }

    public T getVo() {
        return vo;
    }

    public String getGUID() {
        return GUID;
    }
}
