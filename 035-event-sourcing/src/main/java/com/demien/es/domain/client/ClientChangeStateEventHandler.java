package com.demien.es.domain.client;

import com.demien.es.system.EventHandler;
import com.demien.es.system.SpringContext;

public class ClientChangeStateEventHandler implements EventHandler<ClientChangeStateEvent> {

    private ClientFinder getClientFinder() {
        return (ClientFinder) SpringContext.getBean(ClientFinder.class);
    }


    @Override
    public void processEvent(ClientChangeStateEvent event) {

        try {
            ClientEntity client = getClientFinder().findEntityById(event.getRequest().getClientId()).get(0);
            client.processStateChange(event.getRequest().getState());
        } catch (Exception e) {
            event.markAsFailed(e.getMessage());
        }

    }

}
