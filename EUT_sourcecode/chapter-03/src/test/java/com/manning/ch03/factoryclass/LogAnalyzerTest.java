package com.manning.ch03.factoryclass;

import org.junit.Assert;
import org.junit.Test;

import com.manning.ch03.StubExtensionManager;

public class LogAnalyzerTest {
	@Test
	public void nameShorterThan6CharactersIsNotValidEvenWithSupportedExtension() {
		StubExtensionManager fake = new StubExtensionManager();
		fake.shouldExtensionsBeValid = true;
		
		ExtensionManagerFactory.setInstance(fake);

		LogAnalyzer log = new LogAnalyzer();

		Assert.assertFalse(log.isValidLogFileName("short.ext"));
	}
}
