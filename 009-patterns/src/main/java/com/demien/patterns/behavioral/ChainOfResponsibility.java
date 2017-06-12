package com.demien.patterns.behavioral;

/*
   Actors: list (chain) of command objects. Command can have link to "next"(in chain) command.
   Goal: each command tries to do some action and switch execution process to next command.
*/
public class ChainOfResponsibility {

	public abstract class AbstractCommand {
		public abstract String executeInternal();
		
		AbstractCommand next;
		String name;
		
		public void setName(String name) {
			this.name=name;
		}
		
		public void setNext(AbstractCommand next) {
			this.next=next;
		}
				
		
		public String execute(String commands) {
			String result="";
			if (commands.indexOf(name)>-1) {
				result=executeInternal();
			}			
			if (next!=null) {
				result=result+"."+next.execute(commands);
			}
			return result;
		}
		
	}

	public class CreateCommand extends AbstractCommand {

		@Override
		public String executeInternal() {
			return "create";
		}		
		
	}	
	
	public class DrawCommand extends AbstractCommand {

		@Override
		public String executeInternal() {
			return "draw";
		}		
		
	}
	
	public class MoveCommand extends AbstractCommand {

		@Override
		public String executeInternal() {
			return "move";
		}		
		
	}
	
	public class Client {
		public String execute(String commands) {
			
			CreateCommand createCommand=new CreateCommand();
			createCommand.setName("create");
			
			DrawCommand drawCommand=new DrawCommand();
			drawCommand.setName("draw");
			
			MoveCommand moveCommand=new MoveCommand();
			moveCommand.setName("move");
			
			createCommand.setNext(drawCommand);
			drawCommand.setNext(moveCommand);
			
			return createCommand.execute(commands);
		}
	}
	

}
