package com.demien.patterns.behavioral;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StrategyTest {

	@Test
	public void test() {
		Strategy strategy=new Strategy();
		Strategy.FastDrawStrategy fastDrawStrategy=strategy. new FastDrawStrategy();
		Strategy.SlowDrawStrategy slowDrawStrategy=strategy. new SlowDrawStrategy();
        Strategy.Context context=strategy. new Context();

        context.setStrategy(fastDrawStrategy);
        assertEquals("fast draw", context.executeStrategy());

        context.setStrategy(slowDrawStrategy);
        assertEquals("slow draw", context.executeStrategy());

	}

}
