package com.demien.sboot.domain;

public interface IPersistable<ID> {
    ID getId();
    void setId(ID id);
}
