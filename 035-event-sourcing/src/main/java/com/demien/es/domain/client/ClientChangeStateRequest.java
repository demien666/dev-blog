package com.demien.es.domain.client;

public class ClientChangeStateRequest {
    private final long clientId;
    private final long userId;
    private final ClientState state;


    public ClientChangeStateRequest(long clientId, long userId, ClientState state) {
        this.clientId = clientId;
        this.userId = userId;
        this.state = state;
    }

    public long getClientId() {
        return clientId;
    }

    public long getUserId() {
        return userId;
    }

    public ClientState getState() {
        return state;
    }
}
