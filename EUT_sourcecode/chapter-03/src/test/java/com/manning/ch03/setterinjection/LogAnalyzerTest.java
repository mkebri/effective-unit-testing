package com.manning.ch03.setterinjection;

import org.junit.Assert;
import org.junit.Test;

import com.manning.ch03.StubExtensionManager;


public class LogAnalyzerTest {
	@Test
	public void nameShorterThan6CharactersIsNotValidEvenWithSupportedExtension() {
		StubExtensionManager fake = new StubExtensionManager();
		fake.shouldExtensionsBeValid = true;

		LogAnalyzer log = new LogAnalyzer();
		log.setExtensionManager(fake);

		Assert.assertFalse(log.isValidLogFileName("short.ext"));
	}
}
