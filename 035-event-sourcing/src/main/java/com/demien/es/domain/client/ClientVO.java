package com.demien.es.domain.client;

public class ClientVO {
    private final long id;
    private final String name;
    private final String contactInfo;

    public ClientVO(long id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public ClientVO(String name, String contactInfo) {
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
        return "ClientVO{" +
                "name='" + name + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                '}';
    }
}
