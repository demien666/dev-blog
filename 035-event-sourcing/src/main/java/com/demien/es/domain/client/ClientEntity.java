package com.demien.es.domain.client;

import com.demien.es.domain.Entity;

import java.util.Date;

public class ClientEntity extends Entity {

    private long id;
    private String name;
    private String contactInfo;
    private Date createdDate = new Date();
    private ClientState state = ClientState.PENDING;

    public ClientEntity(ClientVO vo) {
        this.name = vo.getName();
        this.contactInfo = vo.getContactInfo();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public ClientState getState() {
        return state;
    }

    public void setState(ClientState state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void update(ClientVO payload) {
        this.name = payload.getName();
        this.contactInfo = payload.getContactInfo();
    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", createdDate=" + createdDate +
                ", state=" + state +
                '}';
    }
}
