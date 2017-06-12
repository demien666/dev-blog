package com.demien.patterns.behavioral;

import static org.junit.Assert.*;

import org.junit.Test;

public class ObserverTest {

	@Test
	public void test() {
		Observer observer=new Observer();
		Observer.FirstHandler firstHandler=observer. new FirstHandler();
		Observer.SecondHandler secondHandler=observer. new SecondHandler();
		Observer.EventSource eventSource=observer. new EventSource();
		eventSource.addEventHandler(firstHandler);
		eventSource.addEventHandler(secondHandler);
		eventSource.createEvent("test");
		assertEquals("first:test;second:test;", observer.getLog());
		
	}

}
