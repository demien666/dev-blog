package com.demien.cxfrest.domain;

public class Region {
	private Integer regionId;
	private String regionName;

	public Region() {
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	@Override
	public String toString() {
		return "[REGION_ID=" + getRegionId().toString() + " REGION_NAME="
				+ getRegionName() + "]";
		// return "["+getRegionId().toString()+"] "+getRegionName();
	}

}
