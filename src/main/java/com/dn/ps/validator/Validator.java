package com.dn.ps.validator;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class Validator {

	List<String> results = Collections.emptyList();

	private Validator() {

	}

	public static Validator start() {
		return new Validator();
	}

	Validator notNull(final Supplier<?> getter, final String  message) {
		getter.get();
		
	return this;
	}

}
