package com.demien.hibgeneric.domain;

import java.io.Serializable;
import javax.persistence.Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.demien.hibgeneric.swing.TableView;

@Entity (name = "LOCATIONS")
public class Location implements Serializable, IDisplayable {

	private static final long serialVersionUID = 3477913378452357045L;
	private final static String[] COLUMN_NAMES={"Location Id", "Street Adress", "Postal Code", "City", "State provice", "Country"};

	@Id
    @Column(name = "LOCATION_ID")
    private Integer locationId;
    
    @Column(name = "STREET_ADDRESS")
    private String streetAdress;
    
    @Column(name = "POSTAL_CODE")
    private String postalCode;
    
    @Column(name = "CITY")
    private String city;
    
    @Column(name = "STATE_PROVINCE")
    private String stateProvince;
    
    @ManyToOne
    @JoinColumn(name = "COUNTRY_ID")
    private Country country;
    
    
    public Location() {
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
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
        return "[ locationId=" + locationId + ", streetAdress=" + streetAdress + ", postalCode=" + postalCode + ", city=" + city + ", stateProvince=" + stateProvince + ", country=" + country + ']';
    }

    @Override
    public String[] getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    public Object[] getColumnValues() {
        Object[] result=new Object[COLUMN_NAMES.length];
        result[0]=getLocationId();
        result[1]=getStreetAdress();
        result[2]=getPostalCode();
        result[3]=getCity();
        result[4]=getStateProvince();
        result[5]=getCountry();
        return result;
    }

    @Override
    public Class<?>[] getColumnClasses() {
        Class<?>[] result=new Class[COLUMN_NAMES.length];
        result[0]=Integer.class;
        result[1]=String.class;
        result[2]=String.class;
        result[3]=String.class;
        result[4]=String.class;
        result[5]=Country.class;
        return result;
    }

    @Override
    public void restore(Object[] values) {
        if (values!=null) {
            setLocationId((Integer)values[0]);
            setStreetAdress((String)values[1]);
            setPostalCode((String)values[2]);
            setCity((String)values[3]);
            setStateProvince((String)values[4]);
            setCountry((Country)values[5]);
        }
    }

	@Override
	public TableView<?> getSelectForm() {
		// TODO Auto-generated method stub
		return new TableView<Location>("Please, select location", Location.class);
	}


}
