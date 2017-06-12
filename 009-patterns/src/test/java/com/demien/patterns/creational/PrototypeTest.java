package com.demien.patterns.creational;

import org.junit.Assert;
import org.junit.Test;

public class PrototypeTest {
	
	@Test
	public void test() throws CloneNotSupportedException {
		Prototype prototype=new Prototype();
		
		Prototype.ComplexObject complexObject1= prototype. new ComplexObject("name", "description", "departent");
		Prototype.ComplexObject complexObject2=complexObject1.clone();
		
		Assert.assertEquals(complexObject1.getName(), complexObject2.getName());
		Assert.assertEquals(complexObject1.getDescription(), complexObject2.getDescription());
		Assert.assertEquals(complexObject1.getDepartent(), complexObject2.getDepartent());
		
	}

}
