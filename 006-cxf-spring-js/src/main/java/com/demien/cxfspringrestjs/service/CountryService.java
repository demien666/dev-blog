package com.demien.cxfspringrestjs.service;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.demien.cxfspringrestjs.domain.Country;

@WebService(serviceName = "countryService")
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Path("/countryService")
public class CountryService extends BaseService<Country> {
	
	public CountryService() {
		super(Country.class);
	}	

}
