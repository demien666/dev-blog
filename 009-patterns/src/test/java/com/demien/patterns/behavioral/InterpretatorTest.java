package com.demien.patterns.behavioral;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.demien.patterns.behavioral.Interpretator.Operator;

public class InterpretatorTest {
	
	Interpretator interpretator=new Interpretator();
	Interpretator.PlusOperator plusOperator=interpretator. new PlusOperator();
	Interpretator.MinusOperator minusOperator=interpretator. new MinusOperator();
	Interpretator.Context context;
	
	@Before
	public void init() {
		Map<String, Operator> operatorMap=new HashMap<String, Operator>();
		operatorMap.put("+", plusOperator);
		operatorMap.put("-", minusOperator);
		context=interpretator. new Context(operatorMap);
	}

	@Test
	public void getNearestOperatorPositionTest() {
		String expression="0123+34";
		int result=context.getNearestOperatorPosition(expression, 0);
		assertEquals(4, result);
		expression="012345-34";
		result=context.getNearestOperatorPosition(expression, 0);
		assertEquals(6, result);
	}
	
	@Test
	public void getNearestOperandEndTest() {
		String expression="0123+56";
		int result=context.getNearestOperandEnd(expression, 0);
		assertEquals(4, result);
		expression="0123+56";
		result=context.getNearestOperandEnd(expression, 5);
		assertEquals(7, result);
	}	
	
	@Test
	public void mainTestWithTraditionalOperators() {
			
		String expression="0123+56+8";
		int result=context.calculate(expression);
		assertEquals(123+56+8, result);
		
		expression="12+34-56";
		result=context.calculate(expression);
		assertEquals(12+34-56, result);
	}
	
	@Test
	public void mainTestWithExperimentalOperators() {
		Map<String, Operator> operatorMap=new HashMap<String, Operator>();
		operatorMap.put("P", plusOperator);
		operatorMap.put("M", minusOperator);
		context=interpretator. new Context(operatorMap);
		
		
		String expression="2P3M1";
		int result=context.calculate(expression);
		assertEquals(4, result);
		
	}	

}
