package com.demien.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.demien.cxfspringrestjs.domain.BaseDomain;
import com.demien.cxfspringrestjs.service.BaseService;
import com.demien.cxfspringrestjs.utils.ObjectDataPopulator;
import com.demien.cxfspringrestjs.utils.RestTestClient;
import com.demien.cxfspringrestjs.utils.RestTestServer;

public abstract class BaseServiceTest<T extends BaseDomain> {
	protected static RestTestServer server = new RestTestServer("JUNIT");
	protected static RestTestClient client = new RestTestClient();

	protected final String BASE_URL = "http://localhost:"
			+ RestTestServer.TEST_PORT + RestTestServer.TEST_CONTEXT
			+ "/services/rest/";
	protected final String SERVICE_URL;
	private final Class<T> cl;

	public BaseServiceTest(String sevicePrexif, Class<T> cl) {
		SERVICE_URL = BASE_URL + sevicePrexif + "/";
		this.cl = cl;
	}

	private T getTestEntity() throws InstantiationException,
			IllegalAccessException {
		T entity = cl.newInstance();
		ObjectDataPopulator.populate(entity);
		return entity;
	}

	@BeforeClass
	public static void init() throws Exception {
		server.start();
	}

	@AfterClass
	public static void finish() throws Exception {
		server.stop();
	}

	@Test
	public void sayHello() throws Exception {
		String value = client.sendGetStringResult(SERVICE_URL
				+ BaseService.SERVICE_HELLO);
		System.out.println("value=" + value);
		assertEquals(BaseService.HELLO, value);
	}

	@SuppressWarnings("unchecked")
	private T getById(Integer id) throws Exception {
		T result = (T) client.sendGetObjectResult(SERVICE_URL
				+ BaseService.SERVICE_GETBYID + "?id=" + id, cl);
		return result;
	}

	private void addEntity(T entity) throws Exception {
		HttpResponse response = client.sendPost(SERVICE_URL
				+ BaseService.SERVICE_ADD, entity);
		int responseCode = response.getStatusLine().getStatusCode();
		assertEquals(RestTestClient.RESPONSE_OK, responseCode);
	}

	private void updateEntity(T entity) throws Exception {
		HttpResponse response = client.sendPost(SERVICE_URL
				+ BaseService.SERVICE_UPDATE, entity);
		int responseCode = response.getStatusLine().getStatusCode();
		assertEquals(RestTestClient.RESPONSE_OK, responseCode);
	}

	private void deleteEntity(T entity) throws Exception {
		HttpResponse response = client.sendPost(SERVICE_URL
				+ BaseService.SERVICE_DELETE, entity);
		int responseCode = response.getStatusLine().getStatusCode();
		assertEquals(RestTestClient.RESPONSE_OK, responseCode);
	}

	@SuppressWarnings({ "unchecked" })
	private List<T> getEntityList() throws IllegalStateException, IOException,
			Exception {

		List<T> result = (List<T>) client.sendGetObjectResult(SERVICE_URL
				+ BaseService.SERVICE_ENTITY_LIST, cl, true);
		return result;
	}

	@Test
	public void addEntityTest() throws Exception {
		T testEntity = getTestEntity();
		addEntity(testEntity);

		T resultEntity = getById(testEntity.getId());
		assertNotNull(resultEntity);
		assertEquals(testEntity, resultEntity);
	}

	@Test
	public void updateEntityTest() throws Exception {
		T testEntity = getTestEntity();
		addEntity(testEntity);
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
		Integer id = testEntity.getId();

		deleteEntity(testEntity);

		T searchEntity = getById(id);
		assertNull(searchEntity);
	}

	@Test
	public void getEntityListTest() throws Exception {
		T testEntity1 = getTestEntity();
		addEntity(testEntity1);
		T testEntity2 = getTestEntity();
		addEntity(testEntity2);

		List<T> entityList = getEntityList();
		assertNotNull(entityList);
		assertTrue(entityList.contains(testEntity1));
		assertTrue(entityList.contains(testEntity2));

	}

}
