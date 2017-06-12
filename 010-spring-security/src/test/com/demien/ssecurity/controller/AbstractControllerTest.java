package com.demien.ssecurity.controller;

import com.demien.ssecurity.testapp.utils.RestTestClient;
import com.demien.ssecurity.testapp.utils.RestTestServer;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by dmitry on 29.09.14.
 */
public class AbstractControllerTest {
    protected static RestTestServer server = new RestTestServer("JUNIT");
    protected static RestTestClient client = new RestTestClient();
    protected final String BASE_URL = "http://localhost:"
            + RestTestServer.TEST_PORT + RestTestServer.TEST_CONTEXT
            + "/services/rest/";
    protected final String SERVICE_URL = BASE_URL + "";


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
                + "hello");
        System.out.println("value=" + value);
        Assert.assertEquals("hello world!", value);
    }


}
