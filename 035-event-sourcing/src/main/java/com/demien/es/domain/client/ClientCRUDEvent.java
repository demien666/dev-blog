package com.demien.es.domain.client;

import com.demien.es.system.event.Event;
import com.demien.es.system.event.EventType;

public class ClientCRUDEvent extends Event<ClientVO, ClientEntity> {
    public ClientCRUDEvent(EventType type, ClientVO payload) {
        super(type, payload);
    }
}
