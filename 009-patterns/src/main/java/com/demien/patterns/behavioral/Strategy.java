package com.demien.patterns.behavioral;

/*
Actors:
 1. Strategy object
 2. Context object which have Strategy object
 Goal: context execute action using strategy object so execution is based on strategy
*/


public class Strategy {
	
	interface DrawStrategy {
		String draw();
	}
	
	public class FastDrawStrategy implements DrawStrategy {
		@Override
		public String draw() {
			return "fast draw";
		}		
	}

	public class SlowDrawStrategy implements DrawStrategy {
		@Override
		public String draw() {
			return "slow draw";
		}		
	}	
	
	public class Context {
		DrawStrategy strategy;

        public void setStrategy(DrawStrategy strategy) {
            this.strategy=strategy;
        }
		
		public String executeStrategy() {
			return strategy.draw();
		}
	}
	
}
