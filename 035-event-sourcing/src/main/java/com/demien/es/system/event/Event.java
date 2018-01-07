package com.demien.es.system.event;

public abstract class Event<REQ, RESP> {
    private String GUID;
    private EventState state = EventState.CREATED;
    private final EventType type;
    private final REQ request;
    private RESP response;
    private String errorMessage;

    public Event(EventType type, REQ request) {
        this.type = type;
        this.request = request;
    }

    public void setGUID(String GUID) {
        this.GUID = GUID;
    }

    public void setState(EventState state) {
        this.state = state;
    }

    public String getGUID() {
        return GUID;
    }

    public EventState getState() {
        return state;
    }

    public REQ getRequest() {
        return request;
    }

    public RESP getResponse() {
        return response;
    }

    public void setResponse(RESP response) {
        this.response = response;
    }

    public EventType getType() {
        return type;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void markAsProcessed(RESP response) {
        this.setState(EventState.PROCESSED);
        this.setResponse(response);

    }

    public void markAsFailed(String message) {
        this.setState(EventState.FILED);
        this.setErrorMessage(message);
    }

}
