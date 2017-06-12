package com.demien.cxfrest.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.demien.cxfrest.domain.Region;

@Path("/customerservice/")
@Produces("text/xml")
public class MyRestService {

	@GET
	@Path("/customers/{id}/")
	public Region getCustomer(@PathParam("id") String id) {
		System.out.println("----invoking getCustomer, Customer id is: " + id);
		Region r = new Region();
		r.setRegionId(Integer.parseInt(id));
		r.setRegionName("hello");
		return r;
	}
	
	@GET
	@Path("/hello")
	public Response sayHello() {
	   return Response.ok("Hello!").build();	
	}

}
