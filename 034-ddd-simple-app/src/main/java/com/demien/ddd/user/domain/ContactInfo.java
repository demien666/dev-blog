package com.demien.ddd.user.domain;

import com.demien.ddd.application.annotations.DDDValueObject;

@DDDValueObject
public class ContactInfo {
    private final ContactInfoType type;
    private final String info;

    public ContactInfo(ContactInfoType type, String info) {
        this.type = type;
        this.info = info;
    }

    public ContactInfoType getType() {
        return type;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactInfo that = (ContactInfo) o;

        if (type != that.type) return false;
        return info != null ? info.equals(that.info) : that.info == null;

    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }
}
