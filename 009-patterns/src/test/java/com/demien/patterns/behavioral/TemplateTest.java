package com.demien.patterns.behavioral;

import static org.junit.Assert.*;

import org.junit.Test;

public class TemplateTest {

	@Test
	public void test() {
		Template template=new Template();
		Template.FastShape fastShape=template. new FastShape();
		Template.SlowShape slowShape=template. new SlowShape();
		//
		String result="";
		result=fastShape.createAndDraw();
		assertEquals("fast create.fast draw", result);
		result=slowShape.createAndDraw();
		assertEquals("slow create.slow draw", result);
		
	}

}
