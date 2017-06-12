package com.demien.patterns.creational;

/* Actors: class which implements Clonable interface
 * Goal: sometimes when object has a lot of properties, it's easier to clone object than 
 *  execute setXXX a lot of times 
 */

public class Prototype {
	
	public class ComplexObject implements Cloneable{
				
		private String name;
		private String description;
		private String department;
		
		public ComplexObject(String name, String description, String department) {
			super();
			this.name = name;
			this.description = description;
			this.department = department;
		}
		
		public ComplexObject clone() throws CloneNotSupportedException {
			return (ComplexObject)super.clone();
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getDepartent() {
			return department;
		}

		public void setDepartent(String departent) {
			this.department = departent;
		}
		
		
		
	
	}
	
	

}
