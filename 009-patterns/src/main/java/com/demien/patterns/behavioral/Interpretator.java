package com.demien.patterns.behavioral;

import java.util.HashMap;
import java.util.Map;
/*
Actors:
1. custom command language
2. expression written on this language
3. interpretator class which can interpret expression oin proper way and calculate it
Goal: calculating expression
*/

public class Interpretator {
	
	interface Operator {
		int calculate(int argument1, int argument2);
	}
	
	class PlusOperator implements Operator {
		
		@Override
		public int calculate(int argument1, int argument2) {			
			return argument1+argument2;
		}
		
	}
	
    class MinusOperator implements Operator {
		
		@Override
		public int calculate(int argument1, int argument2) {			
			return argument1-argument2;
		}
		
	}
    
    class Context {
    	
    	Map<String, Operator> operatorMap;
    	
    	public Context(Map<String, Operator> operatorMap) {
    		if (operatorMap==null) {
    			throw new RuntimeException("OperatorMap can not be null.");
    		}
    		this.operatorMap=operatorMap;	
    	}
    	
    	
    	public int getNearestOperatorPosition(String expression, int position) {
    		int result=-1;
    		for (String operator:operatorMap.keySet()) {
    			int currentOperatorPosition=expression.indexOf(operator, position);
    			if (result==-1 || (currentOperatorPosition!=-1 && currentOperatorPosition<result) ) { 
    				result=currentOperatorPosition;			
    			}
    		}
    		return result;
    	}
    	
        public int getNearestOperandEnd(String expression, int position) {
        	int operandEnd=-1;
    		int nextOperatorPosition=getNearestOperatorPosition(expression, position);
    		if (nextOperatorPosition==-1){
    			operandEnd=expression.length();
    		} else {
    			operandEnd=nextOperatorPosition;
    		}
    		return operandEnd;
    	}
    	
    	public int calculate(String expression) {
    		if (expression==null || expression.length()==0) {
    			return 0;
    		}
    		   		
    		int position=getNearestOperandEnd(expression, 0);
    		String operandStringValue=expression.substring(0, position);
    		int result=Integer.parseInt(operandStringValue);
    		//position++;
    		while (position<expression.length()) {
    			String operator=expression.substring(position, position+1);
    			Operator e=operatorMap.get(operator);    			
    			position++;
    			int operandEnd=getNearestOperandEnd(expression, position);
    			operandStringValue=expression.substring(position, operandEnd);
    			int operand=Integer.parseInt(operandStringValue);
    			result=e.calculate(result, operand);
    			position=operandEnd;
    		}
    		
    		return result;
    	}
    }
    
    

}
