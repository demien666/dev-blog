package com.demien.ssecurity.testapp.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demien.ssecurity.testapp.model.AbstractModel;
import com.demien.ssecurity.testapp.utils.JsonHelper;
import com.demien.ssecurity.testapp.utils.JsonHelper.JsonHelperException;

public class AbstractController<T extends AbstractModel, PK extends Serializable> {

	public static final String HELLO = "hello";

	public static final String SERVICE_HELLO = "sayhello";
	public static final String SERVICE_GET_ALL = "getall";
	public static final String SERVICE_GETBYID = "getbyid";
	public static final String SERVICE_ADD = "add";
	public static final String SERVICE_UPDATE = "update";
	public static final String SERVICE_DELETE = "delete";

	private Class<T> cl;

	public AbstractController(Class<T> cl) {
		this.cl = cl;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/sayhello")
	@ResponseBody
	public String sayHello() {
		System.out.println("Executing sayHello");
		return HELLO;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getall")
	public @ResponseBody
	String getAll() throws JsonHelperException, InstantiationException,
			IllegalAccessException {
		T entity = (T) cl.newInstance();
		List<T> list = (List<T>) entity.getAll();
		String json = JsonHelper.object2json(list);
		return json;
	}

	private T getEntityById(PK id) throws InstantiationException,
			IllegalAccessException {
		T entity = (T) cl.newInstance();
		return (T) entity.get(id);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/getbyid")
	public @ResponseBody
	String getEntity(@RequestParam("id") PK id) throws JsonHelperException,
			InstantiationException, IllegalAccessException {
		T entity = (T) cl.newInstance();
		entity = (T) entity.get(id);
		String resultJson = JsonHelper.object2json(entity);
		return resultJson;
	}

	protected T addEntity(T entity) {
		entity.save();
		return entity;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public @ResponseBody
	String addEntity(@RequestBody String json) throws JsonHelperException {
		T entity = (T) JsonHelper.Json2Object(json, cl);

		if (entity == null) {
			throw new RuntimeException("addEnity Error: entity is null");
		}
        entity.save();
		String resultJson = JsonHelper.object2json(entity);
		return resultJson;
	}

	protected void updateEntity(T entity) {
		entity.update();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST, value = "/update")
	public @ResponseBody
	String updateEntity(@RequestBody String json) throws JsonHelperException {
		T entity = (T) JsonHelper.Json2Object(json, cl);
		updateEntity(entity);
		String resultJson = JsonHelper.object2json(entity);
		return resultJson;
	}

	protected void deleteEntity(T entity) {
		entity.delete();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST, value = "/delete")
	public @ResponseBody
	void deleteEntity(@RequestBody String json) throws JsonHelperException {
		T entity = (T) JsonHelper.Json2Object(json, cl);
		deleteEntity(entity);
	}

}
