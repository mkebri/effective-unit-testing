package com.manning.ch05.roman;

public class RomanNumerals {
	private final static Mapping[] ROMAN_VALUE_TABLE = { new Mapping(1000, "M"),
			new Mapping(900, "CM"), new Mapping(500, "D"), new Mapping(400, "CD"),
			new Mapping(100, "C"), new Mapping(90, "XC"), new Mapping(50, "L"),
			new Mapping(40, "XL"), new Mapping(10, "X"), new Mapping(9, "IX"),
			new Mapping(5, "V"), new Mapping(4, "IV"), new Mapping(1, "I") };

	public static String format(int number) {
		if (number >= 4000 || number < 1) {
			throw new NumberFormatException("Numbers must be in range 1-3999");
		}
		StringBuffer result = new StringBuffer(10);
		for (Mapping equiv : ROMAN_VALUE_TABLE) {
			while (number >= equiv.integer) {
				number -= equiv.integer;
				result.append(equiv.roman);
			}
		}
		return result.toString();
	}

	private static class Mapping {
		public final int integer;
		public final String roman;

		Mapping(int dec, String rom) {
			this.integer = dec;
			this.roman = rom;
		}
	}
}