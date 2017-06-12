package com.demien.patterns.creational;

/** 
 Actors: 
 1.abstract factory - interface which defines factory which can be returned.
 2.concrete factory:  concrete class which implements abstract factory and returns implementations of factories.  
 Goal: 
 create in one step different objects using different creation classes(factories).  
 */
public class AbstractFactory {
	
	public static abstract class AbstractShapeFactory {
		public abstract RedShapeFactory getRedShapeFactory();
		public abstract BlackShapeFactory getBlackShapeFactory();
	}
	
	public static abstract class Drawable {
		protected String pattern;
		public Drawable(String pattern) {
			this.pattern=pattern;
		}
		public abstract String createShape(String pattern);
	}
	
	public static class RedShapeFactory extends Drawable {

		public RedShapeFactory(String pattern) {
			super(pattern);
		}

		@Override
		public String createShape(String pattern) {
			return "red "+pattern;			
		}		
	}
	
	public static class BlackShapeFactory extends Drawable {
		public BlackShapeFactory(String pattern) {
			super(pattern);
		}

		@Override
		public String createShape(String pattern) {
			return "black "+pattern;			
		}		
	}
	
	public static class ShapeFactory extends AbstractShapeFactory {
		private final String pattern;
		
		public ShapeFactory(String pattern) {
			this.pattern=pattern;
		}

		@Override
		public RedShapeFactory getRedShapeFactory() {
			return new RedShapeFactory(pattern);
		}

		@Override
		public BlackShapeFactory getBlackShapeFactory() {
			return new BlackShapeFactory(pattern);
		}
		
	}
	

}
