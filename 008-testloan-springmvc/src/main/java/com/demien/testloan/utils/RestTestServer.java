package com.demien.testloan.utils;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.demien.testloan.AppConfig;

public class RestTestServer {

    public final static String TEST_CONTEXT = "/testloan";
    public final static int TEST_PORT = 8080;
    private Server server;
    private String mode;

    public RestTestServer() {

    }

    public RestTestServer(final String mode) {
        this.mode = mode;
    }
    
    private static WebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        //context.setConfigLocation(CONFIG_LOCATION);
        //context.getEnvironment().setDefaultProfiles(DEFAULT_PROFILE);
        return context;
    }    

    public void start() throws Exception {
        server = new Server(TEST_PORT);

        // Register and map the dispatcher servlet
        ServletHolder servletHolder = new ServletHolder(new DispatcherServlet(getContext()));
        final ServletContextHandler context = new ServletContextHandler();
        context.setContextPath(TEST_CONTEXT);
        // context.setContextPath("/");
        // context.addServlet(servletHolder, "/rest/*");
        
        context.addServlet(servletHolder, "/services/*");
        context.addEventListener(new ContextLoaderListener());

        context.setInitParameter("contextClass", AnnotationConfigWebApplicationContext.class.getName());
        context.setInitParameter("contextConfigLocation", AppConfig.class.getName());

        server.setHandler(context);
        server.start();
        System.out.println("Server started at http://localhost:" + TEST_PORT + TEST_CONTEXT);
        if (mode == null || !mode.equals("JUNIT")) {
            server.join();
        }

    }

    public void stop() throws Exception {
        if (server != null) {
            server.stop();
            server.join();
            server.destroy();
            server = null;
        }
    }

}
