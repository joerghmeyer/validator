package com.dn.ps.validator;

public class Person {
	String name = "Name";
	Car car;
	
	int age;

	String getName() {
		return name;
	}

	Car getCar() {
		return car;
	}
	
	void setName(String name) {
		this.name = name;
	}

	String getNull() {
		return null;
	}

	int getAge() {
		return 24;
	}

	Double getSalary() {
		return 23.4d;
	}
	
	Long getNullLong() {
		return null;
	}
}
