package com.demien.ddd.user.domain;

import com.demien.ddd.base.annotations.DDDValueObject;

@DDDValueObject
public class ContactInfo {
    private final ContactInfoType type;
    private final String info;

    public ContactInfo(ContactInfoType type, String info) {
        this.type = type;
        this.info = info;
    }
}
