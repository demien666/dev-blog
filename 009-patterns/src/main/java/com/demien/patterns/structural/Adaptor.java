package com.demien.patterns.structural;

/*  Actors:
  1. "convenient" interface
  2. adaptor class which adapt "non-convenient" classes to match "convenient" interface
  Goal: using methods of interface on class which doesn't implements that interface using adaptor class

*/
public class Adaptor {
	
	public interface Movable {
		String move();
	}
	
	public class Shape {
		private String name;
		
		public Shape(String name) {
			this.name=name;
		}
		
		public String relocate() {
			return "shape "+name+" is relocated";
		}
	}
	
	public class ShapeToMovableAdaptor implements Movable {
		
		private Shape shape;
		
		public ShapeToMovableAdaptor(Shape shape) {
			this.shape=shape;
		}

		@Override
		public String move() {			
			return shape.relocate();
		}
		
	}

}
