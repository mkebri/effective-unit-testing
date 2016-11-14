package com.manning.ch02;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExpectedExceptionTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void thisTestPasses() { }

	@Test
	public void throwsExceptionAsExpected() {
		thrown.expect(NullPointerException.class);
		throw new NullPointerException();
	}

	@Test
	public void throwsExceptionWithCorrectMessage() {
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("boom");
		throw new NullPointerException("Ka-boom!");
	}
}