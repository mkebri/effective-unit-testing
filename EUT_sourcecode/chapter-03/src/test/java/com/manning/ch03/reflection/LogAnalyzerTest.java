package com.manning.ch03.reflection;

import com.manning.ch03.StubExtensionManager;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

public class LogAnalyzerTest {
	@Test
	public void overridePrivateModifierOfField() {
		StubExtensionManager fake = new StubExtensionManager();
		fake.shouldExtensionsBeValid = true;
		
		LogAnalyzer log = new LogAnalyzer();
		injectToField(log, "extensions", fake);
		
		Assert.assertTrue(log.isValidLogFileName("validLogFile.ext"));
	}

	private void injectToField(Object target, String fieldName,
			Object dependency) {
		try {
			Field field = target.getClass().getDeclaredField(fieldName);
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			field.set(target, dependency);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
