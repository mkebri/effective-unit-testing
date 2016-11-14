package com.manning.ch02;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExceptionWithExpectedRootCauseTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void throwsExceptionWithCorrectCause() {
		thrown.expect(rootCauseOf(NullPointerException.class));
		throw new RuntimeException("Ka-boom!", new NullPointerException());
	}

	private Matcher<? extends Throwable> rootCauseOf(
			final Class<? extends Throwable> expectedCause) {
		return new BaseMatcher() {
			@Override
			public boolean matches(Object candidate) {
				if (!(candidate instanceof Throwable)) {
					return false;
				}
				Throwable cause = ((Throwable) candidate).getCause();
				if (cause == null) {
					return false;
				}
				return expectedCause.isAssignableFrom(cause.getClass());
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("Throwable with cause of type ");
				description.appendText(expectedCause.getSimpleName());
			}
		};
	}
}