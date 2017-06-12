package com.demien.patterns.behavioral;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChainOfResponsibilityTest {

	@Test
	public void test() {
		ChainOfResponsibility chainOfResponsibility=new ChainOfResponsibility();
		ChainOfResponsibility.Client client=chainOfResponsibility. new Client();
		
		String result=client.execute("move, create");
		assertEquals("create..move", result);
		
	}

}
