package com.demien.patterns.structural;

import static org.junit.Assert.*;


import org.junit.Test;

public class DecoratorTest {

	@Test
	public void test() {

        String result=new Decorator.BigDecorator(new Decorator.RedDecorator(new Decorator.Shape())).drawBig();


		assertEquals("shape is red and big", result);
		
	}

}
