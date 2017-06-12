package com.demien.patterns.structural;

/*
  Actors:
    1. Implementation
    2. Abstraction - which depends(aggregate) on Implementation (for instance, Implementation set in constructor)
    3. Concrete classes which extends Abstraction
  Goal: implementation separated from abstraction
 */
public class Bridge {
	
	interface Implementation {
		String move();
	}
	
	class FastImplementation implements Implementation {
		@Override
		public String move() {
			return "fast";
		}		
	}
	
	class SlowImplementation implements Implementation {
		@Override
		public String move() {
			return "slow";
		}		
	}	
	
	
	public abstract class Abstraction{
		
		Implementation implementation;
		
		public Abstraction(Implementation implementation) {
			this.implementation=implementation;
		}
		
		public String rellocate() {
			return implementation.move() +" rellocation";
		}
		
	}
	
	public class ConcreteSlowClass extends Abstraction {

		public ConcreteSlowClass() {
			super(new SlowImplementation());
		}
		
	}
	
	public class ConcreteFastClass extends Abstraction {

		public ConcreteFastClass() {
			super(new FastImplementation());
		}
		
	}	

}
