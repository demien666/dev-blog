package com.demien.es.domain.client;

import com.demien.es.system.EventHandler;
import com.demien.es.system.event.EventState;
import com.demien.es.system.event.EventType;

public class ClientEventHandler implements EventHandler<ClientCRUDEvent> {

    private final ClientRepository clientRepository;

    public ClientEventHandler(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public void processEvent(ClientCRUDEvent event) {
        if (event.getType() == EventType.CREATE) {
            processCreate(event);
            return;
        }

        if (event.getType() == EventType.UPDATE) {
            processUpdate(event);
            return;
        }

    }

    private void processUpdate(ClientCRUDEvent event) {
        ClientEntity entity = clientRepository.findById(event.getPayload().getId());
        if (entity.getState() != ClientState.APPROVED) {
            event.setErrorMessage("Client is in a wrong state! State should be APPROVED instead of " + entity.getState());
            event.setState(EventState.FILED);
            return;
        }

        entity.update(event.getPayload());
        clientRepository.save(entity);
        event.setResponse(entity);
        event.setState(EventState.PROCESSED);
    }

    private void processCreate(ClientCRUDEvent event) {
        ClientEntity entity = new ClientEntity(event.getPayload());
        clientRepository.save(entity);
        event.setResponse(entity);
        event.setState(EventState.PROCESSED);
    }
}
