package com.demien.patterns.behavioral;

import static org.junit.Assert.*;

import org.junit.Test;

public class StateTest {

	@Test
	public void test() {
		State state=new State();
		
		State.Shape shape=state. new Shape();
		shape.setState(state. new CreateShapeState() );
		
		assertEquals("create", shape.executeAction());
		
        shape.setState(state. new DrawShapeState() );
		
		assertEquals("draw", shape.executeAction());
		
		
		
		
	}

}
