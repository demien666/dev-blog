package com.demien.cxfspringrestjs.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.demien.cxfspringrestjs.domain.BaseDomain;
import com.demien.cxfspringrestjs.utils.JsonHelper;
import com.demien.cxfspringrestjs.utils.JsonHelper.JsonHelperException;

public class BaseService<T extends BaseDomain> {

	private List<T> entityList = new ArrayList<T>();
	public static final String HELLO = "Hello world!";
	
	public static final String SERVICE_HELLO="sayhello";
	public static final String SERVICE_ENTITY_LIST="entitylist";
	public static final String SERVICE_GETBYID="getbyid";
	public static final String SERVICE_ADD="add";
	public static final String SERVICE_UPDATE="update";
	public static final String SERVICE_DELETE="delete";
	
	private Class<T> cl;
	
	public BaseService(Class<T> cl) {
		this.cl=cl;
	}

	@GET
	@Path("/sayhello")
	public Response sayHello() {
		System.out.println("Executing sayHello");
		return Response.ok(HELLO).header("Access-Control-Allow-Origin","*").build();
	}

	@GET
	@Path("/entitylist")
	@Produces("application/json")
	public Collection<T> getEntityList() {
		System.out.println("Executing getEntityList");
		return entityList;
	}

	public T getEntityById(Integer id) {
		T result = null;
		for (T entity : entityList) {
			if (entity.getId().equals(id)) {
				result = entity;
			}
		}
		return result;
	}

	@GET
	@Path("/getbyid")
	@Produces("application/json")
	public Response getEntity(@QueryParam("id") Integer id) {
		System.out.println("Executing getEntityById");
		T result =getEntityById(id);
		return Response.ok(result).build();
	}

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addEntity(String json) throws JsonHelperException  {
		System.out.println("Executing addEntity");
		T entity=(T)JsonHelper.Json2Object(json, cl);
		
		T searchResult = getEntityById(entity.getId());
		if (searchResult == null) {
			entityList.add(entity);
		}
		return Response.ok(entity).build();
	}

	@POST
	@Path("/update")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateEntity(String json) throws JsonHelperException {
		System.out.println("Executing updateEntity");
		T entity=(T)JsonHelper.Json2Object(json, cl);
		T forUpdate = getEntityById(entity.getId());
		if (forUpdate != null) {
			forUpdate = entity;
		}
		return Response.ok(forUpdate).build();
	}

	@POST
	@Path("/delete")
	@Consumes("application/json")
	@Produces("application/json")
	public Response deleteEntity(String json) throws JsonHelperException {
		System.out.println("Executing delete entity");
		T entity=(T)JsonHelper.Json2Object(json, cl);
		entityList.remove(entity);
		return Response.ok().build();
	}

}
