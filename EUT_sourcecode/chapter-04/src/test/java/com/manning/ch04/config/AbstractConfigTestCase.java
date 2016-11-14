package com.manning.ch04.config;

import org.junit.Before;


public abstract class AbstractConfigTestCase {
	protected Configuration c;

	@Before
	public void instantiateDefaultConfiguration() {
		c = new Configuration();
		c.processArguments(args());
	}

	protected String[] args() {
		return new String[] {};
	}
}