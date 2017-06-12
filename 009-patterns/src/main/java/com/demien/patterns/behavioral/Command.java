package com.demien.patterns.behavioral;

import java.util.List;

/*
  Actors:
  1. executor- interface which have to be implemented by "command" object
  2. command - implementation of executor interface
  3. receiver - "helper" object which used by command object
  Goal: implementation of "handler": in Java we can not pass function as parameter to another
    function, but be can pass command object and execute such a function on it
 */
public class Command {

	public interface Executor {
		String execute();
	}
	
	public class Receiver {
		public String draw() {
			return "draw";
		}
		public String erase() {
			return "erase";
		}
	}
	
	public abstract class AbstractCommand implements Executor {
		protected Receiver receiver=new Receiver();
	} 
	
	public class DrawCommand extends AbstractCommand {
		
		@Override
		public String execute() {
			return receiver.draw();
		}
		
	}
	public class EraseCommand extends AbstractCommand {

		@Override
		public String execute() {
			return receiver.erase();
		}		
	}
	
	public class Client {
		public String doSomeAction(List<Executor> commands) {
			StringBuilder result=new StringBuilder();
			
			for (Executor eachCommand:commands) {
				result.append(eachCommand.execute()+".");
			}
			
			return result.toString();
		}
	}
	
}
