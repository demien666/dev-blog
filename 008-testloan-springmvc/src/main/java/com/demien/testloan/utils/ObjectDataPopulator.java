package com.demien.testloan.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.demien.testloan.domain.IPersistable;


public class ObjectDataPopulator {

	public static Object populate(final IPersistable instance)
			throws Exception {
		instance.setId(getRandomInteger());
		List<Field> fields = getAllFields(instance);
		for (Field eachField : fields) {
			eachField.setAccessible(true);
			if (eachField.getType().equals(Integer.class)) {
				eachField.set(instance, getRandomInteger());
			} else if (eachField.getType().equals(String.class)) {
				eachField.set(instance, getRandomString());
			} else if (eachField.getType().equals(Float.class)) {
				eachField.set(instance, getRandomFloat());
			} else if (eachField.getType().equals(Date.class)) {
				eachField.set(instance, getRandomDate());	
			} else if (eachField.getType().equals(Set.class)) {	
		     // here should be generators for other standard types like Float, Long....
			} else {
                // processing aggregated objects
				if (eachField.getType().newInstance() instanceof IPersistable) {
					//eachField.set(instance, populate((IPersistable) eachField.getType().newInstance()));
					
				}   				
			}
		}

		return instance;

	}

	private static Integer getRandomInteger() {
		return new Integer((int) (Math.random() * 1000));
	}
	
	private static Float getRandomFloat() {
		return new Float( Math.random() * 1000+Math.random());
	}
	
	private static Date getRandomDate() {
		return new Date(  new Date().getTime() - (int)(Math.random() * 1000*60*60*24*100) );
	}

	private static String getRandomString() {
		StringBuffer result = new StringBuffer();
		String[] letters = new String[] { "A", "B", "C", "D", "E", "F", "G" };
		int length = (int) (Math.random() * 15) + 5;
		for (int i = 0; i < length; i++) {
			int pos = (int) (Math.random() * letters.length);
			result.append(letters[pos]);
		}
		return result.toString();
	}

	private static List<Field> getAllFields(final Object instance) {
		Field[] fields = instance.getClass().getDeclaredFields();
		List<Field> result = new ArrayList<Field>();
		for (int i = 0; i < fields.length; i++) {
			if (!java.lang.reflect.Modifier.isFinal(fields[i].getModifiers())
					&& !java.lang.reflect.Modifier.isStatic(fields[i]
							.getModifiers())) {
				result.add(fields[i]);
			}
		}
		return result;
	}
}
