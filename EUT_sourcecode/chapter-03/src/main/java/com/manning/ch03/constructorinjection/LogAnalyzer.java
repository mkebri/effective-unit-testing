package com.manning.ch03.constructorinjection;

import com.manning.ch03.ExtensionManager;
import com.manning.ch03.FileExtensionManager;
import com.manning.ch03.FileHelper;

public class LogAnalyzer {
	private ExtensionManager extensions;

	public LogAnalyzer() {
		this(new FileExtensionManager());
	}

	protected LogAnalyzer(ExtensionManager extensions) {
		this.extensions = extensions;
	}

	public boolean isValidLogFileName(String fileName) {
		return extensions.isValid(fileName)
				&& FileHelper.basenameWithoutExtension(fileName).length() > 5;
	}
}
