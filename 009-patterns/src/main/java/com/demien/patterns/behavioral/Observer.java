package com.demien.patterns.behavioral;

import java.util.ArrayList;
import java.util.List;

/*
Actors:
 1. event source(observable) - class which has method for registering (adding) observers
 2. event handler(observer) - class which has method for reacting on event
Goal: event source is notifying observers objects
*/

public class Observer {
	
	StringBuilder log=new StringBuilder();
	
	public String getLog() {
		return log.toString();
	}
	
	public interface EventHandler {
		void onEvent(String event);
	}
	
	public class FirstHandler implements EventHandler {
		@Override
		public void onEvent(String event) {
			log.append("first:"+event+";");			
		}		
	}
	
	public class SecondHandler implements EventHandler {
		@Override
		public void onEvent(String event) {
			log.append("second:"+event+";");			
		}		
	}	
	
	public class EventSource {
		List<EventHandler> eventHandlers=new ArrayList<EventHandler>();
		
		public void addEventHandler(EventHandler handler) {
			eventHandlers.add(handler);
		}
		
		public void createEvent(String event) {
			for (EventHandler eventHandler: eventHandlers) {
				eventHandler.onEvent(event);
			}
		}
	}
	

}
