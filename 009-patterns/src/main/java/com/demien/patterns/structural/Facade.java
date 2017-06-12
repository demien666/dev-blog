package com.demien.patterns.structural;

/*
  Actors:
  1. set of classes  (library)
  2. facade class for managing them
  Goal: interface for library: one class which manage other classes
 */
public class Facade {
	
	public interface Drawable {
		public String draw();
	}
	
	public class Square implements Drawable {

		@Override
		public String draw() {			
			return "square.";
		}
		
	}
	
	public class Circle implements Drawable {

		@Override
		public String draw() {			
			return "circle.";
		}
		
	}
	
	
	public class ComplexDrawingFacade {
				
		public String drawComplexObject() {
			Square square1=new Square();
			Square square2=new Square();
			Circle circle=new Circle();
			
			String result=square1.draw()+circle.draw()+square2.draw();
			
			return result;		
		}
		
	}

}
