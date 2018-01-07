package com.demien.es.domain.client;

public class ClientChangeStateRequest {
    private final long clientId;
    private final ClientState state;


    public ClientChangeStateRequest(long clientId, ClientState state) {
        this.clientId = clientId;
        this.state = state;
    }

    public long getClientId() {
        return clientId;
    }

    public ClientState getState() {
        return state;
    }
}
