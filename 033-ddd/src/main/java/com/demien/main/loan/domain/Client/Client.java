package com.demien.main.loan.domain.client;

import com.demien.ddd.annotations.AggregateRoot;
import com.demien.ddd.base.AbstractEntity;
import com.demien.main.shared.domain.ContactInfo;

import java.util.Date;

@AggregateRoot
public class Client extends AbstractEntity {

    private String name;
    private String lastName;

    private AddressInfo addressInfo;
    private ContactInfo contactInfo;

    private Date changed;

    public AddressInfo getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(AddressInfo addressInfo) {
        this.addressInfo = addressInfo;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }
}
