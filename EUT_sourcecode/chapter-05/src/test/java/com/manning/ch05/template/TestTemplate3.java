package com.manning.ch05.template;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTemplate3 {
	@Test
	public void emptyTemplate() throws Exception {
		assertTemplateRendersAsItself("");
	}

	@Test
	public void plainTextTemplate() throws Exception {
		assertTemplateRendersAsItself("plaintext");
	}

	private void assertTemplateRendersAsItself(String template) {
		assertEquals(template, new Template(template).evaluate());
	}
}
