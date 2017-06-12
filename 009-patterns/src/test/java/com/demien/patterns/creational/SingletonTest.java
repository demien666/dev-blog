package com.demien.patterns.creational;

import org.junit.Assert;

import org.junit.Test;

public class SingletonTest {
	
	@Test
	public void test() {
		Singleton s1=Singleton.INSTANCE;
		Singleton s2=Singleton.INSTANCE;
		s1.data="Hello world!";
		Assert.assertEquals(s1.data, s2.data);		
	}
}
