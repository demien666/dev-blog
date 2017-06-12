package com.demien.testloan.service;

import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.demien.testloan.dao.IBaseDAO;
import com.demien.testloan.domain.IPersistable;
import com.demien.testloan.utils.ObjectDataPopulator;

public abstract class BaseServiceImplTest<T extends IPersistable> {
	private Class<T> cl;
	private IBaseService<T> service;
	private IBaseDAO<T> dao;
	T testEntity;
	
	public BaseServiceImplTest(Class<T> cl, IBaseDAO<T> dao, IBaseService<T> service) {
		this.cl = cl;
		this.dao=dao;
		this.service=service;
	}

	@Before
	public void init() throws Exception {
		testEntity = cl.newInstance();
		ObjectDataPopulator.populate(testEntity);
	}

	@Test
	public void getTest() throws Exception {
		Integer id=1;
		service.get(id);
		verify(dao).get(id);
	}
	
	@Test
	public void saveTest() {
		service.save(testEntity);
		verify(dao).save(testEntity);
	}
	
	@Test
	public void updateTest() {
		service.update(testEntity);
		verify(dao).update(testEntity);
	}
	
	@Test
	public void deleteTest() {
		service.delete(testEntity);
		verify(dao).delete(testEntity);
	}
	
	@Test
	public void queryTest() {
		String hsql="sql";
		Map<String, Object> params=new HashMap<String, Object>();
		service.query(hsql, params);
		verify(dao).query(hsql, params);
	}
	
	@Test
	public void getAllTest() {
		String hsql="from "+cl.getName();
		service.getAll();
		verify(dao).query(hsql, null);
	}
	
	@Test
	public void deleteAllTest() {
		String hsql="delete from "+cl.getName();
		service.deleteAll();
		verify(dao).query(hsql, null);
	}
	

}
