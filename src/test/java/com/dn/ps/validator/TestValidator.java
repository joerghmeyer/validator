package com.dn.ps.validator;

import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Assert;
import org.junit.Test;

public class TestValidator {

	Person p = new Person();

	@Test
	public void test01() {
		Validator v = Validator.start().notNull(p::getName);
		Assert.assertEquals("validation should be successfull", v.isValid(), true);
		Assert.assertEquals(true, v.getValidationMessages().isEmpty());
	}

	@Test
	public void test02() {
		Validator v = Validator.start().notNull(p::getNull).notNull(p::getName, "name must not be null");
		Assert.assertEquals("validation should fail", v.isValid(), false);
		Assert.assertEquals(false, v.getValidationMessages().isEmpty());
	}

	@Test
	public void test03() {
		Validator v = Validator.start().notNull(p::getNull);
		Assert.assertEquals("validation should fail", v.isValid(), false);
		Assert.assertEquals(false, v.getValidationMessages().isEmpty());
		System.out.println(v.getValidationMessages());
	}

	@Test
	public void test04() {
		Person p = new Person();
		p.setName(null);

		Validator v = Validator.start().notNull(p::getNull, "this value should not be null").notNull(p::getName,
				"name must not be null");
		Assert.assertEquals("validation should fail", v.isValid(), false);
		Assert.assertEquals(false, v.getValidationMessages().isEmpty());
		System.out.println(v.getValidationMessages());
	}
	
	@Test
	public void test05() {
		Validator v = Validator.start();
		v.checkNumberRange(p::getAge,1,100, "should be betweem 1 and 100");
		v.checkNumberRange(p::getAge,1.0,100L, "should be betweem 1 and 100");
		v.checkNumberRange(p::getAge,-1,100, "should be betweem 1 and 100");
		Assert.assertEquals("validation should not fail", v.isValid(), true);
		Assert.assertEquals(true, v.getValidationMessages().isEmpty());
	}
	
	@Test
	public void test06() {
		Validator v = Validator.start();
		v.checkNumberRange(p::getAge,null,100, "left number range is null");
		Assert.assertEquals("validation should  fail", v.isValid(), false);
		Assert.assertEquals(false, v.getValidationMessages().isEmpty());
		System.out.println(v.getValidationMessages());
	}
	
	@Test
	public void test07() {
		Validator v = Validator.start();
		v.checkNumberRange(p::getAge,1,null, "left number range is null");
		Assert.assertEquals("validation should  fail", v.isValid(), false);
		Assert.assertEquals(false, v.getValidationMessages().isEmpty());
		System.out.println(v.getValidationMessages());
	}
	
	
	@Test
	public void test08() {
		Validator v = Validator.start();
		v.checkNumberRange(p::getNullLong,1,null, "supplier yields null");
		Assert.assertEquals("validation should  fail", v.isValid(), false);
		Assert.assertEquals(false, v.getValidationMessages().isEmpty());
		System.out.println(v.getValidationMessages());
	}
	
	@Test
	public void test09() {
		Validator v = Validator.start();
		v.checkNumberRange(p::getAge ,25,100, "age must be between 25 and 100");
		Assert.assertEquals("validation should  fail", v.isValid(), false);
		Assert.assertEquals(false, v.getValidationMessages().isEmpty());
		System.out.println(v.getValidationMessages());
	}
	
	@Test
	public void test10() {
		Validator v = Validator.start();
		v.checkNumberRange(p::getAge,1,23, "age must be between 1 and 23");
		Assert.assertEquals("validation should fail", v.isValid(), false);
		Assert.assertEquals(false, v.getValidationMessages().isEmpty());
		System.out.println(v.getValidationMessages());
	}
	
	@Test
	public void test11() {
		Validator v = Validator.start();
		v.matches(p::getName,"N[ma]*e");
		Assert.assertEquals("validation should not fail", true, v.isValid());
		Assert.assertEquals(true, v.getValidationMessages().isEmpty());
	}
	
	@Test
	public void test12() {
		Validator v = Validator.start();
		v.matches(p::getName,"N[x]*e");
		Assert.assertEquals("validation should fail", v.isValid(), false);
		Assert.assertEquals(false, v.getValidationMessages().isEmpty());
		System.out.println(v.getValidationMessages());
	}
	
	@Test
	public void test13() {
		Validator v = Validator.start();
		v.matches(p::getName,"N[x]]*e");
		Assert.assertEquals("validation should fail", v.isValid(), false);
		Assert.assertEquals(false, v.getValidationMessages().isEmpty());
		System.out.println(v.getValidationMessages());
	}
	
	@Test
	public void test14() {
		Validator v = Validator.start();
		
		<Car>v.notNull(p::getCar,"car clor should be set", Car::getColor,);
		Assert.assertEquals("validation should fail", v.isValid(), false);
		Assert.assertEquals(false, v.getValidationMessages().isEmpty());
		System.out.println(v.getValidationMessages());
	}
	
}
