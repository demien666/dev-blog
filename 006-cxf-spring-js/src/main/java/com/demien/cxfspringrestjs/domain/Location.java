package com.demien.cxfspringrestjs.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement(name = "Location")
public class Location extends BaseDomain implements Serializable {

	private static final long serialVersionUID = 3477913378452357045L;
	private String streetAdress;
	private String postalCode;
	private String city;
	private String stateProvince;
	private Country country;

	public Location() {
	}

	public String getStreetAdress() {
		return streetAdress;
	}

	public void setStreetAdress(String streetAdress) {
		this.streetAdress = streetAdress;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateProvince() {
		return stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "[ Id=" + getId() + ", streetAdress=" + streetAdress
				+ ", postalCode=" + postalCode + ", city=" + city
				+ ", stateProvince=" + stateProvince + ", country=" + country
				+ ']';
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Location)) {
			return false;
		}
		Location location = (Location) o;
		if (this.getId().equals(location.getId())) {
			return true;
		} else {
			return false;
		}
	}

}
