package com.demien.patterns.structural;

import java.util.ArrayList;
import java.util.List;

/*
  Actors:
  1. interface
  2. simple object which implements interface
  3. complex object(contain set of simple objects) with implements interface
  Goal:
  Do some logic with set(array) of objects like one single object
 */

public class Composite {

	public interface drawable {
		String draw();
	}

	public class SimpleObject implements drawable {

		String name;

		@Override
		public String draw() {
			return "drawing " + name;
		}

		public SimpleObject(String name) {
			this.name = name;
		}

	}

	public class CompositeObject implements drawable {

		List<Composite.SimpleObject> objects=new ArrayList<Composite.SimpleObject>();

		public void addObject(Composite.SimpleObject object) {
			objects.add(object);
		}

		@Override
		public String draw() {
			StringBuilder result = new StringBuilder();

			for (Composite.SimpleObject object : objects) {
				result.append(object.draw() + ".");
			}

			return result.toString();
		}

	}

}
