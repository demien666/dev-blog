package com.demien.sparktest;

import com.demien.sparktest.domain.IPersistable;
import com.demien.sparktest.service.GenericService;
import com.demien.sparktest.util.JsonUtil;
import com.demien.sparktest.util.ObjectPopulator;
import com.demien.sparktest.util.RestTestUtil;
import org.junit.*;
import spark.Spark;

import java.util.List;

public abstract class GenericControllerIT<T extends IPersistable> {
    private String fullPath;
    private Class<T> cl;
    private GenericService<T> service;

    public GenericControllerIT(String basePath, Class<T> cl, GenericService<T> service){
        this.fullPath= App.APP_PATH+basePath;
        this.cl=cl;
        this.service=service;
    }

    @BeforeClass
    public static void init() {
        App.main(null);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void tearDown() {
        Spark.stop();
    }

    @Before
    public void initTest() {
        service.clearStorage();
    }

    T getTestObject() throws Exception {
        T testObject=cl.newInstance();
        ObjectPopulator.populate(testObject);
        return testObject;
    }

    @Test
    public void addTest() throws Exception {
        T testObject=getTestObject();
        RestTestUtil.RequestResult res= RestTestUtil.sendRequest("POST", fullPath, JsonUtil.toJson(testObject));
        Assert.assertEquals(200, res.status);
        Assert.assertEquals(service.getById(testObject.getId()), testObject);
    }

    @Test
    public void getTest() throws Exception {
        T testObject=getTestObject();
        service.add(testObject);
        RestTestUtil.RequestResult res= RestTestUtil.sendRequest("GET", fullPath +"/"+testObject.getId());
        Assert.assertEquals(200, res.status);
        T receivedObject=(T)JsonUtil.toObject(res.body, cl);
        Assert.assertEquals(testObject, receivedObject);
    }

    @Test
    public void getAllTest() throws Exception {
        T testObject1=getTestObject();
        service.add(testObject1);
        T testObject2=getTestObject();
        service.add(testObject2);
        RestTestUtil.RequestResult res= RestTestUtil.sendRequest("GET", fullPath);
        Assert.assertEquals(200, res.status);
        List<T> receivedObjectList=(List<T>)JsonUtil.toObject(res.body, java.util.List.class);
        Assert.assertTrue(receivedObjectList.size()==2);
    }


    @Test
    public void updateTest() throws Exception {
        T testObject=getTestObject();
        service.add(testObject);
        T updatedObject=getTestObject();
        updatedObject.setId(testObject.getId());

        RestTestUtil.RequestResult res= RestTestUtil.sendRequest("PUT", fullPath, JsonUtil.toJson(updatedObject));
        Assert.assertEquals(200, res.status);

        T updatedObjectFromService=service.getById(testObject.getId());
        Assert.assertEquals(updatedObject, updatedObjectFromService);
    }

    @Test
    public void deleteTest() throws Exception {
        T testObject=getTestObject();
        service.add(testObject);

        RestTestUtil.RequestResult res= RestTestUtil.sendRequest("DELETE", fullPath, JsonUtil.toJson(testObject));
        Assert.assertEquals(200, res.status);

        T deletedObject=service.getById(testObject.getId());
        Assert.assertNull(deletedObject);
    }





}
