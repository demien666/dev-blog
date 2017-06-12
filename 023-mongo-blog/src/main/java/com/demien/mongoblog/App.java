package com.demien.mongoblog;

import com.demien.mongoblog.controller.LoginController;
import spark.Spark;

import java.io.IOException;

/**
 * Created by demien on 1/31/16.
 */
public class App {
    static Context context=new Context();

    public static void main(String[] args) throws IOException {
        App app=new App();
        app.routeInit();
    }

    public static Context getContext() {
        return context;
    }

    public void routeInit() throws IOException {

        Spark.get("/", new LoginController.SignUpRoute("signup.ftl"));
    }
}
