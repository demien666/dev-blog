package com.demien.es.domain.client;

public class ClientCRUDRequest {
    private final long id;
    private final String name;
    private final String contactInfo;

    public ClientCRUDRequest(long id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public ClientCRUDRequest(String name, String contactInfo) {
        this(-1, name, contactInfo);
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ClientCRUDRequest{" +
                "name='" + name + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                '}';
    }
}
