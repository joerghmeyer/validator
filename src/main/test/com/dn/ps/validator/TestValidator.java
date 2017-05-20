package com.dn.ps.validator;

import org.junit.Test;

public class TestValidator {

	
	
	@Test
	public void test1() {
		Person p = new Person();
		Validator.start().notNull(p::getName, "message");
	}
}
