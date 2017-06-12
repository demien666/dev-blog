package com.demien.jbehave.steps;

public class Calculator {
	
	public int div(int x, int y) {
		if (y==0) {
			throw new RuntimeException("y can not be 0");
		}
		return (int)(x/y);
	}

}
