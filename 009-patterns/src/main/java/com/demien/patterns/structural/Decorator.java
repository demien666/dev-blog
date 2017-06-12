package com.demien.patterns.structural;

/*
  Actors:
  1. original class
  2. decorator class which wrap original class
  Goal: extend functionality of original class
 */
public class Decorator {
	
	public static class Shape {
		public String draw() {
			return "shape";
		}
	}
	
	public static class RedDecorator  {
		private Shape shape;

        public RedDecorator(Shape shape) {
            this.shape=shape;
        }

		public String drawRed() {
			return shape.draw()+" is red";
			
		}		
	}
	
	public static class BigDecorator {
        private RedDecorator redDecorator;

        public BigDecorator(RedDecorator redDecorator) {
            this.redDecorator=redDecorator;
        }

		public String drawBig() {
			return redDecorator.drawRed()+" and big";
			
		}		
	}	

}
