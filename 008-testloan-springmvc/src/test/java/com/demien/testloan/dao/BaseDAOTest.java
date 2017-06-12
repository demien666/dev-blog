package com.demien.testloan.dao;

import java.io.Serializable;

import junit.framework.Assert;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class BaseDAOTest {
	BaseDAOImpl<TestClass> dao;
	SessionFactory sessionFactory;
	Session session;
	TestClass testObject;
	
	private class TestClass implements Serializable {
		private static final long serialVersionUID = -8877252618686760948L;
	}
	
	@Before
	public void init() {
		testObject=new TestClass();
		
		sessionFactory=mock(SessionFactory.class);
		session=mock(Session.class);
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		
		dao=new BaseDAOImpl<TestClass>(TestClass.class, sessionFactory);
	}
	
	@Test
	public void getTest() {
		Integer id=1;
	    when(session.get(TestClass.class, id)).thenReturn(testObject);
	    Object result=dao.get(id);
	    Assert.assertEquals(testObject, result);
	}
	
	@Test
	public void saveTest() {
		TestClass newObject=new TestClass();
	    when(session.save(testObject)).thenReturn(newObject);
	    Object result=dao.save(testObject);
	    Assert.assertEquals(newObject, result);
	}
	
	@Test
	public void updateTest() {
	    dao.update(testObject);
	    verify(session).update(testObject);
	}
	
	@Test
	public void deleteTest() {
	    dao.delete(testObject);
	    verify(session).delete(testObject);
	}
	
	@Test
	public void queryTest() {
		Query query=mock(Query.class);
		when(session.createQuery(anyString())).thenReturn(query);
		//1 
		dao.query("select * from dual", null);
		verify(query).list();
		//2 
		dao.query("delete from dual", null);
		verify(query).executeUpdate();
		

	}

}
