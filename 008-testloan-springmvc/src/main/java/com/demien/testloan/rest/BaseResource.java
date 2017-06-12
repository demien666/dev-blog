package com.demien.testloan.rest;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demien.testloan.domain.IPersistable;
import com.demien.testloan.service.IBaseService;
import com.demien.testloan.utils.JsonHelper;
import com.demien.testloan.utils.JsonHelper.JsonHelperException;

import static com.demien.testloan.AppConst.*;

public class BaseResource<T extends IPersistable> {

	private IBaseService<T> service;
	
	public static final String SERVICE_HELLO="sayhello";
	public static final String SERVICE_GET_ALL="getall";
	public static final String SERVICE_GETBYID="getbyid";
	public static final String SERVICE_ADD="add";
	public static final String SERVICE_UPDATE="update";
	public static final String SERVICE_DELETE="delete";
	
	
	private Class<T> cl;
	
	public BaseResource(Class<T> cl, IBaseService<T> service) {
		this.cl=cl;
		this.service=service;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/sayhello")
	public @ResponseBody String sayHello() {
		System.out.println("Executing sayHello");
		return HELLO;
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/getAll")
	public @ResponseBody List<T> getAll() {
		List<T> list = service.getAll();
		return list;
	}

	private T getEntityById(Integer id) {
		
		return service.get(id);
		
	}

	@RequestMapping(method=RequestMethod.GET, value="/getbyid")
	public @ResponseBody T getEntity(@PathVariable("id") Integer id) {
		//System.out.println("Executing getEntityById");
		T result =service.get(id);
		return result;
	}
	
		protected T addEntity(T entity) {
		Object o=service.save(entity);
		if (o instanceof Integer) {
			Integer id=(Integer)o;
			entity.setId(id);
		}
		return entity;
	}

	@SuppressWarnings("unchecked")	
	@RequestMapping(method=RequestMethod.POST, value="/add")
	public @ResponseBody T addEntity(String json) throws JsonHelperException  {
		//System.out.println("Executing addEntity");
		T entity=(T)JsonHelper.Json2Object(json, cl);
		
		T searchResult = getEntityById(entity.getId());
		if (searchResult == null) {
			entity=addEntity(entity);
		}
		return entity;
	}
	
	protected void updateEntity(T entity) {
		service.update(entity); 
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method=RequestMethod.POST, value="/update")
	public @ResponseBody T updateEntity(String json) throws JsonHelperException {
		System.out.println("Executing updateEntity");
		T entity=(T)JsonHelper.Json2Object(json, cl);
		updateEntity(entity);
		return entity;
	}
	
	protected void deleteEntity(T entity) {
		service.delete(entity);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method=RequestMethod.POST, value="/delete")
	public @ResponseBody void deleteEntity(String json) throws JsonHelperException {
		System.out.println("Executing delete entity");
		T entity=(T)JsonHelper.Json2Object(json, cl);
		deleteEntity(entity);
	}

}
