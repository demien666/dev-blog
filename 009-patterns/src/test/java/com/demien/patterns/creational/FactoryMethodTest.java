package com.demien.patterns.creational;

import org.junit.Assert;
import org.junit.Test;

public class FactoryMethodTest {
	
	private FactoryMethod factory=new FactoryMethod();
	
	@Test
	public void readSquareTest() {
		String shape=factory.createRedSquareStringObject();
		Assert.assertEquals("red square", shape);
	}
	
	@Test
	public void blackSquareTest() {
		String shape=factory.createBlackSquareStringObject();
		Assert.assertEquals("black square", shape);
	}

}
