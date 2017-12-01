package com.demien.ddd.loan.domain;

import com.demien.ddd.shared.annotations.Entity;
import com.demien.ddd.shared.domain.AbstractEntity;
import com.demien.ddd.shared.domain.ContactInfo;

import java.util.Date;

@Entity
public class Client extends AbstractEntity {

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
