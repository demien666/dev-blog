package com.demien.cxfrest;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

import com.demien.cxfrest.rest.MyRestService;

public class Server {
	protected Server() throws Exception {
		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
		sf.setResourceClasses(MyRestService.class);
		sf.setResourceProvider(MyRestService.class,
				new SingletonResourceProvider(new MyRestService()));
		sf.setAddress("http://localhost:9000/");
		sf.create();
	}

	public static void main(String args[]) throws Exception {
		new Server();
		System.out.println("Server ready...");

		Thread.sleep(5 * 6000 * 1000);
		System.out.println("Server exiting");
		System.exit(0);
	}

}
