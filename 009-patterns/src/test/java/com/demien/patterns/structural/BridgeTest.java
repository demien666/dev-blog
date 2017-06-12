package com.demien.patterns.structural;

import static org.junit.Assert.*;

import org.junit.Test;

public class BridgeTest {

	@Test
	public void test() {
		Bridge bridge=new Bridge();
		
		Bridge.ConcreteFastClass fast=bridge. new ConcreteFastClass();
		assertEquals("fast rellocation", fast.rellocate());
		
		
		Bridge.ConcreteSlowClass slow=bridge. new ConcreteSlowClass();
		assertEquals("slow rellocation", slow.rellocate());
	}

}
