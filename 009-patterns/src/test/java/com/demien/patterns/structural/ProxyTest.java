package com.demien.patterns.structural;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProxyTest {

	@Test
	public void test() {
		Proxy proxy=new Proxy();
		Proxy.RealImplementation remoteObject=proxy. new ProxyImplementation();
		assertEquals("real draw", remoteObject.draw());
		
	}

}
