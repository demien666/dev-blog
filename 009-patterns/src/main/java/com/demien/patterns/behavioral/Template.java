package com.demien.patterns.behavioral;

/**
 Actors:
 1. template class: one method of it can not be overriden - it is a TEMPLATE method
 2. subclasses of template class
 Goal: strictly defined template of logic execution. detail of execution can be different for subclasses,
 by overriding non template methods

 */
public class Template {

	public abstract class ShapeTemplate {
		public abstract String create();

		public abstract String draw();

		public String createAndDraw() {
			return create() + "." + draw();
		}
	}

	public class FastShape extends ShapeTemplate {

		@Override
		public String create() {
			return "fast create";
		}

		@Override
		public String draw() {
			return "fast draw";
		}
	}

	public class SlowShape extends ShapeTemplate {

		@Override
		public String create() {
			return "slow create";
		}

		@Override
		public String draw() {
			return "slow draw";
		}
	}
}
