package com.demien.es.domain.client;

import com.demien.es.system.Entity;

import java.util.Date;

public class ClientEntity extends Entity {

    private String name;
    private String contactInfo;
    private Date createdDate = new Date();
    private ClientState state = ClientState.PENDING;

    public ClientEntity(ClientCRUDRequest vo) {
        super();
        this.name = vo.getName();
        this.contactInfo = vo.getContactInfo();
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

    public void handleUpdate(ClientCRUDRequest payload) throws Exception {
        if (this.getState() != ClientState.APPROVED)
            exception("Client should be in APPROVED state, instead of " + this.getState());
        this.name = payload.getName();
        this.contactInfo = payload.getContactInfo();
    }

    public void handleStateChange(ClientState newState) throws Exception {
        if (newState == this.getState()) exception("Already in this state ");
        this.setState(newState);
    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", createdDate=" + createdDate +
                ", state=" + state +
                '}';
    }
}
