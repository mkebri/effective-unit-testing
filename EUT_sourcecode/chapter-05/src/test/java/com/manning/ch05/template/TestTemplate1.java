package com.manning.ch05.template;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTemplate1 {
	@Test
	public void emptyTemplate() throws Exception {
		assertEquals("", new Template("").evaluate());
	}

	@Test
	public void plainTextTemplate() throws Exception {
		assertEquals("plaintext", new Template("plaintext").evaluate());
	}
}
