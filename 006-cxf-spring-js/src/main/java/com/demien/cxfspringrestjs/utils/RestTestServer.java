package com.demien.cxfspringrestjs.utils;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class RestTestServer {
	public final static String TEST_CONTEXT = "/spring-cxf-rest";
	public final static int TEST_PORT = 8080;
	private Server server;
	private String mode;
	
	public RestTestServer() {
		
	}
	
	public RestTestServer(String mode) {
		this.mode=mode;
	}	

	public void start() throws Exception {

		if (server == null) {
			server = new Server(TEST_PORT);
			WebAppContext context = new WebAppContext();
			context.setDescriptor("src/main/webapp/WEB-INF/web.xml");
			context.setResourceBase("src/main/webapp");
			context.setContextPath(TEST_CONTEXT);
			context.setParentLoaderPriority(true);
			server.setHandler(context);
			server.start();
			System.out.println("Server started at http://localhost:"+TEST_PORT+TEST_CONTEXT);
			if (mode==null || !mode.equals("JUNIT")) {
				server.join();
			}
			//
		}
		//         
	}
	
    public void stop() throws Exception
    {
        if (server != null)
        {
            server.stop();
            server.join();
            server.destroy();
            server = null;
        }
    }
}
