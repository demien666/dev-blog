package com.demien.patterns.behavioral;

import static org.junit.Assert.*;

import org.junit.Test;

public class MediatorTest {

	@Test
	public void test() {
		Mediator mediator=new Mediator();
		Mediator.ShapeMediator shapeMediator=mediator. new ShapeMediator();
		String result=shapeMediator.drawAndMove("square");
		assertEquals("drawing:square.moving:square", result);
	}

}
