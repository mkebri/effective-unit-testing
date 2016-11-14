package com.manning.ch03.constructorinjection.anotherpackage;

import org.junit.Assert;
import org.junit.Test;

import com.manning.ch03.ExtensionManager;
import com.manning.ch03.StubExtensionManager;
import com.manning.ch03.constructorinjection.LogAnalyzer;

public class InstantiatingLogAnalyzerFromAnotherPackageTest {
	private class LogAnalyzerProxy extends LogAnalyzer {
		public LogAnalyzerProxy(ExtensionManager extensions) {
			super(extensions);
		}
	}

	@Test
	public void nameShorterThan6CharactersIsNotValidEvenWithSupportedExtension() {
		StubExtensionManager fake = new StubExtensionManager();
		fake.shouldExtensionsBeValid = true;

		LogAnalyzer log = new LogAnalyzerProxy(fake);

		Assert.assertFalse(log.isValidLogFileName("short.ext"));
	}
}
