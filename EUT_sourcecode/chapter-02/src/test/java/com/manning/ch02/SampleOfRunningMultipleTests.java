package com.manning.ch02;

import org.junit.Test;

public class SampleOfRunningMultipleTests {
	@Test
	public void thisIsTheFirstTest() throws Exception {
		// and it passes with flying colors!
	}

	@Test
	public void anotherTest() throws Exception {
		// TODO: uncomment the following to intentionally fail this test
		// fail("for demonstration purposes...");
	}

	@Test
	public void oneMoreTest() throws Exception {
		// just to show that all tests are run even if one fails
	}
}
