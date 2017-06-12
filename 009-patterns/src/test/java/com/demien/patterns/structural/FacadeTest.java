package com.demien.patterns.structural;

import static org.junit.Assert.*;

import org.junit.Test;

public class FacadeTest {

	@Test
	public void test() {
		Facade facade=new Facade();
		
		Facade.ComplexDrawingFacade compleDrawingFacade=facade. new ComplexDrawingFacade();
		assertEquals("square.circle.square.", compleDrawingFacade.drawComplexObject());
	}

}
