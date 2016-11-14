package com.manning.ch03.factorymethod;

import com.manning.ch03.ExtensionManager;
import com.manning.ch03.FileExtensionManager;
import com.manning.ch03.FileHelper;

public class LogAnalyzer {

	protected ExtensionManager getExtensionManager() {
		return new FileExtensionManager();
	}

	public boolean isValidLogFileName(String fileName) {
		return getExtensionManager().isValid(fileName)
				&& FileHelper.basenameWithoutExtension(fileName).length() > 5;
	}
}
