package com.manning.ch05.roman;

import static com.manning.ch05.roman.RomanNumerals.format;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestRomanNumerals2 {
	private int number;
	private String numeral;

	public TestRomanNumerals2(int number, String numeral) {
		this.number = number;
		this.numeral = numeral;
	}

	@Parameters
	public static Collection<Object[]> data() {
		return asList(new Object[][] { { 1, "I" }, { 2, "II" }, { 4, "IV" },
				{ 5, "V" }, { 7, "VII" }, { 9, "IX" }, { 10, "X" } });
	}

	@Test
	public void formatsPositiveIntegers() {
		assertEquals(numeral, format(number));
	}
}