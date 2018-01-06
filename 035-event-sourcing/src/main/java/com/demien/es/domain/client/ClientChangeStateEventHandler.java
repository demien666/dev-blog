package com.demien.es.domain.client;

import com.demien.es.system.EventHandler;
import com.demien.es.system.event.EventState;

public class ClientChangeStateEventHandler implements EventHandler<ClientChangeStateEvent> {

    private final ClientRepository clientRepository;

    public ClientChangeStateEventHandler(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void processEvent(ClientChangeStateEvent event) {

        if (event.getRequest().getState() == ClientState.APPROVED) {
            processApproval(event);
            return;
        }

    }

    private void processApproval(ClientChangeStateEvent event) {
        ClientEntity client = clientRepository.findById(event.getRequest().getClientId());
        client.setState(event.getRequest().getState());
        event.setResponse(client);
        event.setState(EventState.PROCESSED);

    }
}
