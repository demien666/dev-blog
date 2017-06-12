package com.demien.ssecurity.testapp;

import com.demien.ssecurity.testapp.utils.RestTestServer;

/**
 * Created by dmitry on 29.09.14.
 */
public class StartServer {
    public static void main(String args[]) throws Exception {
        new RestTestServer().start();
    }
}
