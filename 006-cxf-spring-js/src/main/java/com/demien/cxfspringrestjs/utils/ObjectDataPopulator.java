package com.demien.cxfspringrestjs.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.demien.cxfspringrestjs.domain.BaseDomain;
import com.demien.cxfspringrestjs.domain.Country;

public class ObjectDataPopulator {

	public static void main(String[] args) throws IllegalArgumentException,
			IllegalAccessException, InstantiationException {

		Country country = new Country();
		country = (Country) ObjectDataPopulator.populate(country);
		System.out.println(country.toString());
	}

	public static Object populate(BaseDomain instance)
			throws IllegalArgumentException, IllegalAccessException,
			InstantiationException {
		instance.setId(getRandomInteger());
		List<Field> fields = getAllFields(instance);
		for (Field eachField : fields) {
			eachField.setAccessible(true);
			if (eachField.getType().equals(Integer.class)) {
				eachField.set(instance, getRandomInteger());
			} else if (eachField.getType().equals(String.class)) {
				eachField.set(instance, getRandomString());
		     // here should be generators for other standard types like Float, Long....
			} else {
                // processing aggregated objects
				if (eachField.getType().newInstance() instanceof BaseDomain) {
					eachField.set(instance, populate((BaseDomain) eachField
							.getType().newInstance()));
				}				
			}
		}

		return instance;

	}

	private static Integer getRandomInteger() {
		return new Integer((int) (Math.random() * 1000));
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

	private static List<Field> getAllFields(Object instance) {
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
