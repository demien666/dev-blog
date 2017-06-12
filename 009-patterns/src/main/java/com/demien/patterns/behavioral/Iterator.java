package com.demien.patterns.behavioral;

/*
Actors:
   1. iterator interface which describes it operation list
   2. iterator object which implements interface
   3. iterable object - object which has method for returning it iterator
Goal: iterate through  array of elements using iterator object
*/

public class Iterator {

	interface SimpleIterator<T> {
		boolean hasNext();
		T getNext();
	} 
		
	class ArrayIterator<T> implements SimpleIterator<T> {
		T[] array;
		int position=-1;
		
		public ArrayIterator(T[] array) {
			this.array=array;
		}

		@Override
		public boolean hasNext() {
			if (position<array.length-1) {
				return true;
			}
			return false;
		}

		@Override
		public T getNext() {
			position++;
			return array[position];			
		}
		
	}
	
	interface Iterable<T>{
		SimpleIterator<T> getIterator();
	}
	
	class ArrayIterable<T> implements Iterable<T> {
		private T[] array;
		
		public ArrayIterable(T[] array) {
			this.array=array;
		}

		@Override
		public SimpleIterator<T> getIterator() {
			return new ArrayIterator<T>(array);
		}		
	}
		
	
}
