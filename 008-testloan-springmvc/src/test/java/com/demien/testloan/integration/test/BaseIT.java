package com.demien.testloan.integration.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.demien.testloan.AppConst;
import com.demien.testloan.rest.BaseResource;
import com.demien.testloan.domain.IPersistable;
import com.demien.testloan.utils.ObjectDataPopulator;
import com.demien.testloan.utils.RestTestClient;
import com.demien.testloan.utils.RestTestServer;

public abstract class BaseIT<T extends IPersistable> {
	protected static RestTestServer server = new RestTestServer("JUNIT");
	protected static RestTestClient client = new RestTestClient();

	protected final String BASE_URL = "http://localhost:"
			+ RestTestServer.TEST_PORT + RestTestServer.TEST_CONTEXT
			+ "/services/rest/";
	protected final String SERVICE_URL;
	private Class<T> cl;

	public BaseIT(String sevicePrexif, Class<T> cl) {
		SERVICE_URL = BASE_URL + sevicePrexif + "/";
		this.cl=cl;
    }

	@BeforeClass
	public static void init() throws Exception {
		server.start();
	}

	@AfterClass
	public static void finish() throws Exception {
		server.stop();
	}
	
	private T getTestEntity() throws Exception {
      T entity = cl.newInstance();
      ObjectDataPopulator.populate(entity);
    return entity;
   }
	
	@Test
	public void sayHello() throws Exception {
		String value = client.sendGetStringResult(SERVICE_URL
				+ BaseResource.SERVICE_HELLO);
		System.out.println("value=" + value);
		assertEquals(AppConst.HELLO, value);
	}

	@SuppressWarnings("unchecked")
	private T getById(Integer id) throws Exception {
		T result = (T) client.sendGetObjectResult(SERVICE_URL
				+ BaseResource.SERVICE_GETBYID + "?id=" + id, cl);
		return result;
	}

	private void addEntity(T entity) throws Exception {
		HttpResponse response = client.sendPost(SERVICE_URL
				+ BaseResource.SERVICE_ADD, entity);
		int responseCode = response.getStatusLine().getStatusCode();
		assertEquals(RestTestClient.RESPONSE_OK, responseCode);
	}

	private void updateEntity(T entity) throws Exception {
		HttpResponse response = client.sendPost(SERVICE_URL
				+ BaseResource.SERVICE_UPDATE, entity);
		int responseCode = response.getStatusLine().getStatusCode();
		assertEquals(RestTestClient.RESPONSE_OK, responseCode);
	}

	private void deleteEntity(T entity) throws Exception {
		HttpResponse response = client.sendPost(SERVICE_URL
				+ BaseResource.SERVICE_DELETE, entity);
		int responseCode = response.getStatusLine().getStatusCode();
		assertEquals(RestTestClient.RESPONSE_OK, responseCode);
	}

	@SuppressWarnings({ "unchecked" })
	private List<T> getEntityList() throws IllegalStateException, IOException,
			Exception {

		List<T> result = (List<T>) client.sendGetObjectResult(SERVICE_URL
				+ BaseResource.SERVICE_GET_ALL, cl, true);
		return result;
	}

	@Test
	public void addEntityTest() throws Exception {
		int cnt=getEntityList().size();
		T testEntity = getTestEntity();
		addEntity(testEntity);
		assertEquals(cnt+1, getEntityList().size());
	}

	@Test
	public void updateEntityTest() throws Exception {
		T testEntity = getTestEntity();
		addEntity(testEntity);
		testEntity=getEntityList().get(0);
		Integer id = testEntity.getId();

		T forUpdate = getTestEntity();
		forUpdate.setId(id);
		updateEntity(forUpdate);

		T searchEntity = getById(id);
		assertEquals(forUpdate, searchEntity);
	}

	@Test
	public void deleteEntityTest() throws Exception {
		T testEntity = getTestEntity();
		addEntity(testEntity);
		Integer id = getEntityList().get(0).getId();
		testEntity.setId(id);

		deleteEntity(testEntity);

		T searchEntity = getById(id);
		assertTrue(searchEntity==null);
	}

	

}
