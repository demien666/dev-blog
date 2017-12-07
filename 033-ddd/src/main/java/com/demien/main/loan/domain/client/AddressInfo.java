package com.demien.main.loan.domain.client;

import com.demien.ddd.annotations.ValueObject;

@ValueObject
public class AddressInfo {
    private String country;
    private String city;
    private String street;
    private String house;
    private Long flat;

    public AddressInfo(String country, String city, String street, String house, Long flat) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    public Long getFlat() {
        return flat;
    }
}
