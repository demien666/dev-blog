package com.demien.testloan.rest;

import org.junit.Before;
import org.junit.Test;

import com.demien.testloan.domain.IPersistable;
import com.demien.testloan.service.IBaseService;
import com.demien.testloan.utils.JsonHelper;
import com.demien.testloan.utils.ObjectDataPopulator;

import static org.mockito.Mockito.*;

public abstract class BaseResourceTest<T extends IPersistable> {
	Class<T> cl;
	IBaseService<T> service;
	BaseResource<T> resource;
	
	public BaseResourceTest(Class<T> cl) {
		this.cl=cl;
	}
	
	@SuppressWarnings("unchecked")
	@Before
	public void initBase() {
		service=mock(IBaseService.class);
		resource=new BaseResource<T>(cl, service);
	}
	
	@Test
	public void getAllTest() throws Exception {
        resource.getAll();
        verify(service).getAll();
	}
	
	@Test
	public void getByIdTest() {
		Integer id=1;
		resource.getEntity(id);
		verify(service).get(id);		
	}
	
	@Test
	public void addEntityTest() throws Exception {
		T entity=cl.newInstance();
		ObjectDataPopulator.populate(entity);
		String json=JsonHelper.object2json(entity);
		resource.addEntity(json);
		verify(service).save(entity);
			
	}

}
