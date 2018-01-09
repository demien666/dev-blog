package com.demien.es.domain.client;

import com.demien.es.system.EventHandler;
import com.demien.es.system.SpringContext;
import com.demien.es.system.event.EventType;

public class ClientCRUDEventHandler implements EventHandler<ClientCRUDEvent> {

    private ClientFinder getClientFinder() {
        return (ClientFinder) SpringContext.getBean(ClientFinder.class);
    }


    @Override
    public void processEvent(ClientCRUDEvent event) {
        try {
            if (event.getType() == EventType.CREATE) {
                ClientEntity client = new ClientEntity(event.getRequest());
                event.markAsProcessed(client);
                return;
            }

            if (event.getType() == EventType.UPDATE) {
                ClientEntity client = getClientFinder().findEntityById(event.getRequest().getId());
                client.update(event.getRequest());
                event.markAsProcessed(client);
                return;
            }

        } catch (Exception ex) {
            event.markAsFailed(ex.getMessage());
        }

    }

}
