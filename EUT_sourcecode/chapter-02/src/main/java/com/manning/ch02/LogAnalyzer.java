package com.manning.ch02;

public class LogAnalyzer {
	public boolean isValidLogFileName(String fileName) {
		if (fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("No filename provided!");
		}
		return fileName.toLowerCase().endsWith(".slf");
	}
}
