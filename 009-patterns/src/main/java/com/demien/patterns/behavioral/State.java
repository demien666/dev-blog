package com.demien.patterns.behavioral;

/*
 Actors:
 1. set of state objects
 2. object which can be in different states(with different assignments of state object)
 Goal: object behavior depends on it state (state object)
*/
public class State {

	public interface ShapeState {
		String executeAction();
	}

	public class CreateShapeState implements ShapeState {

		@Override
		public String executeAction() {
			return "create";
		}

	}

	public class DrawShapeState implements ShapeState {

		@Override
		public String executeAction() {
			return "draw";
		}

	}

	public class Shape {
		private ShapeState state;

		public ShapeState getState() {
			return state;
		}

		public void setState(ShapeState state) {
			this.state = state;
		}

		public String executeAction() {
			return state.executeAction();
		}
	}

}
