package com.demien.patterns.creational;

import org.junit.Assert;

import org.junit.Test;

public class BuilderTest {
	
	private Builder builder=new Builder(); 
	
	@Test
	public void oneFlootBuildingWithRoofTest() {
		StringBuilder building=new StringBuilder();
		
		builder.addFundament(building);
		builder.addFloor(building);
		builder.addRoof(building);
		
		Assert.assertEquals("fundament floor roof", building.toString());
		 
	}
	
	@Test
	public void threeFlootBuildingWithMansardaTest() {
		StringBuilder building=new StringBuilder();
		
		builder.addFundament(building);
		builder.addFloor(building);
		builder.addFloor(building);
		builder.addFloor(building);
		builder.addMansarda(building);
		
		Assert.assertEquals("fundament floor floor floor mansarda", building.toString());
		 
	}	

}
