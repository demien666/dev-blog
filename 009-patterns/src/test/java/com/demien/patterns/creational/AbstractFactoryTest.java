package com.demien.patterns.creational;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AbstractFactoryTest {
	
	AbstractFactory.ShapeFactory factory;
	String pattern;
	
	@Before
	public void init() {
		pattern="square";
	}

	@Test
	public void blackFactoryTest() {
		factory=new AbstractFactory.ShapeFactory(pattern);
		String result=factory.getBlackShapeFactory().createShape(pattern);
		assertEquals("black square", result);
	}

	@Test
	public void redFactoryTest() {
		factory=new AbstractFactory.ShapeFactory(pattern);
		String result=factory.getRedShapeFactory().createShape(pattern);
		assertEquals("red square", result);
	}
}
