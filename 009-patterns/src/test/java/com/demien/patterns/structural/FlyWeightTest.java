package com.demien.patterns.structural;

import static org.junit.Assert.*;

import org.junit.Test;

public class FlyWeightTest {

	@Test
	public void test() throws InstantiationException, IllegalAccessException {
		
		FlyWeight flyWeight=new FlyWeight();
		FlyWeight.FlyWeightFactory factory=flyWeight. new FlyWeightFactory();
		
		
		FlyWeight.AbstractShape circle=factory.lookUp(FlyWeight.Circle.class);
		FlyWeight.AbstractShape shape=factory.lookUp(FlyWeight.Shape.class);
		
		assertEquals("shape", shape.draw());
		assertEquals("circle", circle.draw());
		
	}

}
