package com.demien.hibgeneric.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;

import com.demien.hibgeneric.swing.TableView;

@Entity(name = "COUNTRIES")
public class Country implements Serializable, IDisplayable {

	private static final long serialVersionUID = 7566184847780781908L;
	private final static String[] COLUMN_NAMES={"Country Id", "Country Name", "Region"};
	
	@Id
	@Column(name = "COUNTRY_ID")
	private String countryId;

	@Column(name = "COUNTRY_NAME")
	private String countryName;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "REGION_ID")
	private Region region;

	public Country() {
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
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

		//return "[COUNTRY_ID=" + getCountryId().toString() + " COUNTRY_NAME="+ getCountryName() + " REGION=" + region.toString() + "]";
		return "["+getCountryId()+"] "+getCountryName();
	}
    @Override
    public String[] getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    public Object[] getColumnValues() {
        Object[] result=new Object[COLUMN_NAMES.length];
        result[0]=getCountryId();
        result[1]=getCountryName();
        result[2]=getRegion();
        return result;
    }

    @Override
    public Class[] getColumnClasses() {
        Class[] result=new Class[COLUMN_NAMES.length];
        result[0]=String.class;
        result[1]=String.class;
        result[2]=Region.class;
        return result;
    }

    @Override
    public void restore(Object[] values) {
        if (values!=null) {
            setCountryId((String)values[0]);
            setCountryName((String)values[1]);
            setRegion((Region)values[2]);
        }
    }

	@Override
	public TableView<?> getSelectForm() {
		// TODO Auto-generated method stub
		return new TableView<Country>("Select country, please", Country.class);
	}

}
