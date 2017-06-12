package com.demien.patterns.creational;

/*
 actors: builder class with methods for adding components to the created object
 goal: create different objects(with different components) in few steps  
 */
public class Builder {
	
	public void addFundament(StringBuilder object) {
		object.append("fundament ");
	} 
	
	public void addFloor(StringBuilder object) {
		object.append("floor ");
	}
	
	public void addMansarda(StringBuilder object) {
		object.append("mansarda");
	}	
	
	public void addRoof(StringBuilder object) {
		object.append("roof");
	}
	
}
