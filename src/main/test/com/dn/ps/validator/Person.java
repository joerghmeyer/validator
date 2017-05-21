package com.dn.ps.validator;

public class Person {
	String name = "Name";
	int age;

	String getName() {
		return name;
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
