package com.dn.ps.validator;

import org.junit.Assert;
import org.junit.Test;

public class TestValidator {

	Person p = new Person();

	@Test
	public void test1() {
		Validator v = Validator.start().notNull(p::getName);
		Assert.assertEquals("validation should be successfull", v.isValid(), true);
		Assert.assertEquals(true, v.getValidationMessages().isEmpty());
	}

	@Test
	public void test2() {
		Validator v = Validator.start().notNull(p::getNull).notNull(p::getName, "name must not be null");
		Assert.assertEquals("validation should fail", v.isValid(), false);
		Assert.assertEquals(false, v.getValidationMessages().isEmpty());
	}

	@Test
	public void test3() {
		Validator v = Validator.start().notNull(p::getNull);
		Assert.assertEquals("validation should fail", v.isValid(), false);
		Assert.assertEquals(false, v.getValidationMessages().isEmpty());
		System.out.println(v.getValidationMessages());
	}

	@Test
	public void test4() {
		Person p = new Person();
		p.setName(null);

		Validator v = Validator.start().notNull(p::getNull, "this value should not be null").notNull(p::getName,
				"name must not be null");
		Assert.assertEquals("validation should fail", v.isValid(), false);
		Assert.assertEquals(false, v.getValidationMessages().isEmpty());
		System.out.println(v.getValidationMessages());
	}
}
