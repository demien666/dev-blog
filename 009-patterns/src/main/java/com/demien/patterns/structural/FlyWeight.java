package com.demien.patterns.structural;

import java.util.HashMap;

/*
 Actors:
 1. set of objects
 2. flyweight(cache) object: contains list of created objects
 Goal: using object cache(for memory minimizing) and object sharing instead of new object creation
*/
public class FlyWeight {
	
	public static abstract class AbstractShape {
		public abstract String draw();
	}
	
	public static class Shape extends AbstractShape {

		@Override
		public String draw() {
			return "shape";
		}		
	}
	
	public static class Circle extends AbstractShape {

		@Override
		public String draw() {
			return "circle";
		}		
	}
	
	public class FlyWeightFactory {
		HashMap<String, AbstractShape> cache=new HashMap<String, AbstractShape>(); 
		public AbstractShape lookUp(Class<?> cl) throws InstantiationException, IllegalAccessException {
			String className=cl.getCanonicalName();
			if (!cache.containsKey(className)) {
				try {
				    Object o=cl.newInstance();
				} catch(Exception e) {
					e.printStackTrace();
				}
				AbstractShape shape=(AbstractShape)cl.newInstance();
				
				cache.put(className, shape);
			} 			
			return cache.get(className);
		}
	}

}
