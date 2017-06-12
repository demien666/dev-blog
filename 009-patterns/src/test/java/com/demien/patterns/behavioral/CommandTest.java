package com.demien.patterns.behavioral;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CommandTest {

	@Test
	public void test() {
		Command command =new Command();
		Command.DrawCommand draw=command. new DrawCommand();
		Command.EraseCommand erase=command. new EraseCommand();
		
		List<Command.Executor> commandList=new ArrayList<Command.Executor>();
		commandList.add(draw);
		commandList.add(draw);
		commandList.add(erase);
		
		Command.Client client=command. new Client();
		assertEquals("draw.draw.erase.", client.doSomeAction(commandList));
		
		
	}

}
