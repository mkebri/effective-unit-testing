package com.manning.ch04.config;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDefaultConfigValues extends AbstractConfigTestCase {
	@Test
	public void defaultOptionsAreSetCorrectly() {
		assertFalse(c.isDebuggingEnabled());
		assertFalse(c.isWarningsEnabled());
		assertFalse(c.isVerbose());
		assertFalse(c.shouldShowVersion());
	}
}