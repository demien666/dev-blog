package com.demien.testloan.utils;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

import com.demien.testloan.domain.IPersistable;
import com.demien.testloan.domain.Loan;

public class ObjectDataPopulatorTest {

	@Test
	public void newtest() throws Exception {
		Loan loan=new Loan();
		ObjectDataPopulator.populate(loan);
		System.out.println(loan);
		
	}
	
	@SuppressWarnings("rawtypes")
	//@Test
	public void populateTest() throws Exception {
		for (Class cl : JsonHelperTest.getDomainClassesList()) {
			Object entity = cl.newInstance();
			ObjectDataPopulator.populate((IPersistable) entity);
			for (Method method : cl.getMethods()) {
				if ((method.getName().startsWith("get"))) {
					Object value=method.invoke(entity);
					Assert.assertTrue(value!=null);
				}
			}

		}
	}

}
