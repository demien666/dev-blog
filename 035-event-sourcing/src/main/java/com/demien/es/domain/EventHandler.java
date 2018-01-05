package com.demien.es.domain;

public interface EventHandler<T> {

    void processEvent(T event);

}
