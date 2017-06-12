package com.demien.patterns.structural;


import static org.junit.Assert.*;

import org.junit.Test;

public class CompositeTest {

	@Test
	public void test() {
		Composite composite=new Composite();
		
		Composite.SimpleObject simple1=composite. new SimpleObject("first");
		assertEquals("drawing first", simple1.draw());
		
		Composite.SimpleObject simple2=composite. new SimpleObject("second");
		
		Composite.CompositeObject complex=composite. new CompositeObject();
		complex.addObject(simple1);
		complex.addObject(simple2);

		assertEquals("drawing first.drawing second.", complex.draw());

	}

}
