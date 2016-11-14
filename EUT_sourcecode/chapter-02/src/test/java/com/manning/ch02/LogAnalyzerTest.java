package com.manning.ch02;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;

public class LogAnalyzerTest {
	private LogAnalyzer analyzer;

	@Before
	public void setUp() {
		analyzer = new LogAnalyzer();
	}

	@Test
	public void fileNameWithCorrectSuffixInUppercaseIsConsideredValid() {
		boolean result = analyzer.isValidLogFileName("whatever.SLF");
		Assert.assertTrue("filename should be valid.", result);
	}

	@Test
	public void fileNameWithCorrectSuffixInLowercaseIsConsideredValid() {
		boolean result = analyzer.isValidLogFileName("whatever.slf");
		Assert.assertTrue("filename should be valid.", result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void emptyFileNameResultsInExceptionBeingThrown() throws Exception {
		analyzer.isValidLogFileName("");
	}

	@Test
	public void exceptionForEmptyFileNameMakesSense() {
		try {
			analyzer.isValidLogFileName("");
			fail("Expected an exception.");
		} catch (IllegalArgumentException expected) {
			Assert.assertEquals("No filename provided!", expected.getMessage());
		}
	}
	
	@Test
	@Ignore
	public void thisTestIsBrokenAndShouldNotBeRun() throws Exception {
		throw new RuntimeException("This should never be thrown");
	}

	@After
	public void tearDown() {
		// there actually isn't anything to clean up right now
	}
}
