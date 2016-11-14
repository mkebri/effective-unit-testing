package com.manning.ch03;

import java.io.File;

public class FileHelper {

	public static String basenameWithoutExtension(String fileName) {
		String basename = new File(fileName).getName();
		if (basename.contains(".")) {
			basename = basename.substring(0, basename.lastIndexOf('.'));
		}
		return basename;
	}
}
