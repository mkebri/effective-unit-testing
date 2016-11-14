package com.manning.ch02;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class HasExpectedExceptionSample {
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void throwsNothing() {
	}

	@Test
	public void throwsNullPointerException() {
		thrown.expect(NullPointerException.class);
		throw new NullPointerException();
	}

	@Test
	public void throwsNullPointerExceptionWithMessage() {
		thrown.expect(NullPointerException.class);
		thrown.expectMessage("happened?");
		throw new NullPointerException("What happened?");
	}
}
