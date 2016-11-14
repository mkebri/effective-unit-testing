package com.manning.ch03.factorymethod;

import org.junit.Assert;
import org.junit.Test;

import com.manning.ch03.ExtensionManager;
import com.manning.ch03.StubExtensionManager;

public class LogAnalyzerTest {
	@Test
	public void nameShorterThan6CharactersIsNotValidEvenWithSupportedExtension() {
		final StubExtensionManager fake = new StubExtensionManager();
		fake.shouldExtensionsBeValid = true;

		LogAnalyzer log = new LogAnalyzer() {
			@Override
			protected ExtensionManager getExtensionManager() {
				return fake;
			}
		};

		Assert.assertFalse(log.isValidLogFileName("short.ext"));
	}

	@Test
	public void nameShorterThan6CharactersIsNotValidEvenWithSupportedExtensions() {
		final StubExtensionManager fake = new StubExtensionManager();
		fake.shouldExtensionsBeValid = true;

		TestableLogAnalyzer log = new TestableLogAnalyzer();
		log.extensionManager = fake;

		Assert.assertFalse(log.isValidLogFileName("short.ext"));
	}
	
	class TestableLogAnalyzer extends LogAnalyzer {
		public ExtensionManager extensionManager;

		@Override
		protected ExtensionManager getExtensionManager() {
			return extensionManager;
		}
	}
}
