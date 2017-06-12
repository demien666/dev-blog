package com.demien.testloan.utils;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.*;

import org.junit.Test;

import com.demien.testloan.domain.IPersistable;
import com.demien.testloan.domain.Loan;
import com.demien.testloan.domain.User;

public class JsonHelperTest {
	
	
	@SuppressWarnings("rawtypes")
	public static List<Class> getDomainClassesList() {
		List<Class> classList=new ArrayList<Class>();
		classList.add(User.class);
		classList.add(Loan.class);
		return classList;
	} 
	
	
	@SuppressWarnings("rawtypes")
	@Test
	public void object2json2objectTest() throws Exception {
		for (Class cl:getDomainClassesList()) {
			Object entity=cl.newInstance();
			ObjectDataPopulator.populate((IPersistable)entity);	
			String json=JsonHelper.object2json(entity);
			Object resultEntity=JsonHelper.Json2Object(json, cl);
			assertEquals(entity, resultEntity);
			// list test
		}		
	}
	
	@Test
	public void jsonListTest() throws Exception {
		ListTester<User> userTest=new ListTester<User>(User.class);
		userTest.test();
		ListTester<Loan> loanTest=new ListTester<Loan>(Loan.class);
		loanTest.test();
	}
	
	private class ListTester<T> {
		private Class<T> cl;
		private int COUNT=10;
		public ListTester(Class<T> cl) {
			this.cl=cl;
		}
		
		@SuppressWarnings("unchecked")
		public void test() throws Exception {
			List<T> elements=new ArrayList<T>();
			for (int i=0;i<COUNT;i++) {
				T element=cl.newInstance();
				ObjectDataPopulator.populate((IPersistable)element);
				elements.add(element);
			}
			
			String json=JsonHelper.object2json(elements);
			List<T> result=(List<T>) JsonHelper.Json2ObjectList(json, cl);
			assertEquals(elements.size(), result.size());
			for (T element:elements) {
				assertTrue(result.contains(element));
			}
		}
	}
	

}
