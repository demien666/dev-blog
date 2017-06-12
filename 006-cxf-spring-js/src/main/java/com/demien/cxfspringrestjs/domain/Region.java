package com.demien.cxfspringrestjs.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement(name = "Region")
public class Region extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 8268800253932817168L;
	private String regionName;

	public Region() {
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	@Override
	public String toString() {
		return "[" + getId().toString() + "] " + getRegionName();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Region)) {
			return false;
		}

		Region region = (Region) o;
		if (this.getId().equals(region.getId())) {
			return true;
		} else {
			return false;
		}
	}

}
