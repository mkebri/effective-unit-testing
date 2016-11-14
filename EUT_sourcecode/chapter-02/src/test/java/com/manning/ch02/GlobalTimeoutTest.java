package com.manning.ch02;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

public class GlobalTimeoutTest {
	/**
	 * Make this time out smaller (e.g. 100ms) and see how the
	 * sleepy tests will fail as soon as that timeout is triggered.
	 */
	@Rule
	public TestRule globalTimeout = new Timeout(1500);

	@Test
	public void aSlowTest() throws InterruptedException {
		Thread.sleep(1000);
	}

	@Test
	public void anotherSlowTest() throws InterruptedException {
		Thread.sleep(1000);
	}
}
