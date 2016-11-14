package com.manning.ch04.logs;

public class LogAnalyzer {
	private WebService service;

	public LogAnalyzer(WebService service) {
		this.service = service;
	}

	public void analyze(String fileName) {
		if (fileName.length() < 8) {
			try {
				service.logError("Filename too short: " + fileName);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}
