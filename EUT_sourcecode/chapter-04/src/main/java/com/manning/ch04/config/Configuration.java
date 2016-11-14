package com.manning.ch04.config;

public class Configuration {
	private boolean verboseOutput;
	private boolean showVersion;
	private String fileName;
	private boolean showWarnings;
	private boolean showDebug;

	public void processArguments(String[] args) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-f")) {
				fileName = args[++i];
			} else if (args[i].equals("-v")) {
				verboseOutput = true;
			} else if (args[i].equals("-w")) {
				showWarnings = true;
			} else if (args[i].equals("-d")) {
				showDebug = true;
			} else if (args[i].equals("--version")) {
				showVersion = true;
			}
		}
	}

	public boolean isDebuggingEnabled() {
		return showDebug;
	}

	public boolean isWarningsEnabled() {
		return showWarnings;
	}

	public boolean isVerbose() {
		return verboseOutput;
	}

	public boolean shouldShowVersion() {
		return showVersion;
	}

	public String getFileName() {
		return fileName;
	}
}
