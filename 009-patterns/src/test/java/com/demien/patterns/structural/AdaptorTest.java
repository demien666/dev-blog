package com.demien.patterns.structural;

import org.junit.Assert;

import org.junit.Test;

public class AdaptorTest {
	
	@Test
	public void test() {
		Adaptor adaptor=new Adaptor();
		
		Adaptor.Shape shape=adaptor. new Shape("circle");
		
		Adaptor.Movable shapeAdaptor=adaptor. new ShapeToMovableAdaptor(shape);
		
		String result=shapeAdaptor.move();
		
		Assert.assertEquals("shape circle is relocated", result);
	}

}
