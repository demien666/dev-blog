package com.demien.es.domain.client;

import com.demien.es.system.EntityFinder;
import com.demien.es.system.EventBus;

public class ClientFinder extends EntityFinder<ClientEntity> {
    public ClientFinder(EventBus eventBus) {
        super(eventBus, ClientEntity.class);
    }
}
