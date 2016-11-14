package com.manning.ch03.factoryclass;

import com.manning.ch03.ExtensionManager;
import com.manning.ch03.FileHelper;

public class LogAnalyzer {
	private ExtensionManager extensions;

	public LogAnalyzer() {
		this.extensions = ExtensionManagerFactory.create();
	}

	public boolean isValidLogFileName(String fileName) {
		return extensions.isValid(fileName)
				&& FileHelper.basenameWithoutExtension(fileName).length() > 5;
	}
}
