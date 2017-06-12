package com.demien.cxfspringrestjs.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement(name = "Country")
public class Country extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 7566184847780781908L;
	private String countryName;
	private Region region;

	public Country() {
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return "[" + getId() + "] " + getCountryName() + " Region:"
				+ getRegion().toString();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Country)) {
			return false;
		}

		Country country = (Country) o;
		if (this.getId().equals(country.getId())) {
			return true;
		} else {
			return false;
		}
	}

}
