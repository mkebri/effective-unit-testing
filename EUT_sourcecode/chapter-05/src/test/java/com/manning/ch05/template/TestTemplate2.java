package com.manning.ch05.template;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTemplate2 {
	@Test
	public void emptyTemplate() throws Exception {
		String template = "";
		assertEquals(template, new Template(template).evaluate());
	}

	@Test
	public void plainTextTemplate() throws Exception {
		String template = "plaintext";
		assertEquals(template, new Template(template).evaluate());
	}
}
