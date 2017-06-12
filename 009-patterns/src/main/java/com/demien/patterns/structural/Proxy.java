package com.demien.patterns.structural;

/*
  Actors:
  1. remote object
  2. local proxy class which has the same interface as remote object
  3. local client, which works with proxy as like it is remote object
  Goal : make client independent of remote object and connection.
  Also proxy object can add some addition functionality, checking and other stuff.
 */
public class Proxy {
	
	interface Drawable {
		String draw();
	}
	
	public class RealImplementation implements Drawable {

		@Override
		public String draw() {
			return "real draw";
		}
		
	}
	
	public class ProxyImplementation extends RealImplementation {
		
		RealImplementation realImplementation=new RealImplementation();

		@Override
		public String draw() {
            // do some logic
			return realImplementation.draw();
            // do some logic
		}
		
	}

}
