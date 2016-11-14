package com.manning.ch03.reflection.anotherpackage;

import java.lang.reflect.Constructor;

import org.junit.Assert;
import org.junit.Test;

import com.manning.ch03.StubExtensionManager;
import com.manning.ch03.reflection.LogAnalyzer;

public class LogAnalyzerTest {
	@Test
	public void overridePrivateModifierOfField() {
		StubExtensionManager fake = new StubExtensionManager();
		fake.shouldExtensionsBeValid = true;

		LogAnalyzer log = instantiate(LogAnalyzer.class, fake);

		Assert.assertTrue(log.isValidLogFileName("validLogFile.ext"));
	}

	private <T> T instantiate(Class<T> target, Object argument) {
		try {
			Constructor<T> c = findConstructor(target, argument.getClass());
			if (!c.isAccessible()) {
				c.setAccessible(true);
			}
			return (T) c.newInstance(argument);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	private <T> Constructor<T> findConstructor(Class<T> target,
			Class<?> argumentType) {
		try {
			for (Constructor<?> c : target.getDeclaredConstructors()) {
				if (c.getParameterTypes().length == 1) {
					Class<?> paramType = c.getParameterTypes()[0];
					if (paramType.isAssignableFrom(argumentType)) {
						return (Constructor<T>) c;
					}
				}
			}
			throw new RuntimeException(target.getName()
					+ " doesn't have a constructor that accepts a "
					+ argumentType.getName());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
