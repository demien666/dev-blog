package com.demien.es.domain.client;

import com.demien.es.system.event.Event;
import com.demien.es.system.event.EventType;

public class ClientChangeStateEvent extends Event<ClientChangeStateRequest, ClientEntity> {

    public ClientChangeStateEvent(ClientChangeStateRequest request) {
        super(request.getState() == ClientState.APPROVED ? EventType.APPROVE : EventType.REJECT, request);
    }
}
