package com.dn.ps.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class Validator {

	private List<String> results = Collections.emptyList();

	protected synchronized void addMessage(final String message1, final String message2) {
		if (results.isEmpty()) {
			results = new ArrayList<>();
		}
		results.add(message1 + " " + getCodePosition()
				+ (message2 == null || message2.length() == 0 ? "" : (" " + message2)));
	}

	protected synchronized void addMessage(final String message1) {
		addMessage(message1, null);
	}

	private Validator() {
	}

	public static Validator start() {
		return new Validator();
	}

	Validator notNull(final Supplier<?> s) {
		return notNull(s, "");
	}

	Validator notNull(final Supplier<?> s, final String message) {
		if (s.get() == null) {
			addMessage("null check failed", message);
		}
		return this;
	}

	public Validator checkNumberRange(final Supplier<Number> s, final Number l, final Number r) {
		return checkNumberRange(s, l, r, null);
	}

	public Validator checkNumberRange(final Supplier<Number> s, final Number l, final Number r, final String message) {
		if (l == null) {
			addMessage("left range value is null");
		}
		if (r == null) {
			addMessage("right range value is null");
		}
		if (s.get() == null) {
			addMessage("value to compare is null");
		}
		if (l != null && s.get() != null && compareTo(l, s.get()) > -1) {
			addMessage("value is smaller than lower bound", message);
		}

		if (l != null && s.get() != null && compareTo(r, s.get()) < 1) {
			addMessage("value is bigger than upper bound", message);
		}

		// if () {
		// addMessage("null check failed at " + getCodePosition() +
		// (message.length() == 0 ? "" : (" " + message)));
		// }
		return this;

	}

	boolean isValid() {
		return results.isEmpty();
	}

	public String getValidationMessages() {
		String result = "";
		for (final String s : results) {
			if (result.isEmpty()) {
				result = s;
			} else {
				result += (System.lineSeparator() + s);
			}

		}
		return result;
	}

	protected String getCodePosition() {
		final String thisCallsName = getClass().getName(); // works also in case
															// Validator is
															// overridden
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		int i = 1;
		while (i < ste.length && thisCallsName.equals(ste[i].getClassName())) {
			i++;
		}

		final String result;
		if (i == ste.length) {
			result = "no code position found";
		} else {
			result = ste[i].getClassName() + "@" + ste[i].getMethodName() + "(file:" + ste[i].getFileName() + ",line:"
					+ ste[i].getLineNumber() + ")";
		}
		return result;
	}

	public int compareTo(final Number n1, final Number n2) {
		// ignoring null handling
		final BigDecimal b1 = new BigDecimal(n1.doubleValue());
		final BigDecimal b2 = new BigDecimal(n2.doubleValue());
		return b1.compareTo(b2);
	}
}
