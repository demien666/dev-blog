package com.demien.patterns.behavioral;

import static org.junit.Assert.*;

import org.junit.Test;

public class IteratorTest {

	@Test
	public void test() {
		String[] array=new String[]{"a", "b", "c"};
		Iterator iterator=new Iterator();
		Iterator.ArrayIterable<String> arrayIterable=iterator. new ArrayIterable<String>(array);
		Iterator.SimpleIterator<String> arrayIterator=arrayIterable.getIterator();
		StringBuilder result=new StringBuilder();
		while (arrayIterator.hasNext()) {
			String element=arrayIterator.getNext();
			result.append(element);
		}
		assertEquals("abc", result.toString());
		
	}

}
