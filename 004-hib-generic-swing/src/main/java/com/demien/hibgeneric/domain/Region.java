package com.demien.hibgeneric.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.demien.hibgeneric.swing.TableView;

@Entity(name = "REGIONS")
public class Region implements Serializable, IDisplayable {

	private static final long serialVersionUID = 8268800253932817168L;
	private final static String COLUMN_NAMES[]={"Region Id", "Region Name"};
	
	@Id
	@Column(name = "REGION_ID")
	private Integer regionId;
	@Column(name = "REGION_NAME")
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
		//return "[REGION_ID=" + getRegionId().toString() + " REGION_NAME="			+ getRegionName() + "]";
		 return "["+getRegionId().toString()+"] "+getRegionName();
	}
    @Override
    public String[] getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    public Object[] getColumnValues() {
        Object[] result=new Object[COLUMN_NAMES.length];
        result[0]=getRegionId();
        result[1]=getRegionName();
        return result;
    }

    @Override
    public Class<?>[] getColumnClasses() {
        Class<?>[] result=new Class[COLUMN_NAMES.length];
        result[0]=Integer.class;
        result[1]=String.class;
        return result;
    }

    @Override
    public void restore(Object[] values) {
 
        if (values!=null) {
            setRegionId((Integer)values[0]);
            setRegionName((String)values[1]);
        }
    }

	@Override
	public TableView<?> getSelectForm() {
		return new TableView<Region>("Select region, please", Region.class);
	}
	

}
