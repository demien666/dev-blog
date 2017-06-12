package com.demien.cxfspringrestjs.service;


import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.demien.cxfspringrestjs.domain.Region;

@WebService(serviceName = "regionService")
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Path("/regionService")
public class RegionService extends BaseService<Region>  {
	
	public RegionService() {
		super(Region.class);
	}	

}
