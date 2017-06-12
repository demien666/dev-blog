package com.demien.patterns.behavioral;

/*
Actors:
1. components
2. client
3. mediator
Goal: add some new functionality by combining existing.
   client communicate with mediator but not with components.
   mediator - HUB of components communication. */
public class Mediator {
	
	public class Drawer {
		public String drawShape(String shape) {
			return "drawing:"+shape;
		}
	}
	
	public class Mover {
		public String moveShape(String shape) {
			return "moving:"+shape;
		}
	}
	
	public class ShapeMediator {
		Drawer drawer=new Drawer();
		Mover mover=new Mover();
		
		public String drawAndMove(String shape) {
			return drawer.drawShape(shape)+"."+mover.moveShape(shape);
		}
	}
	
	

}
