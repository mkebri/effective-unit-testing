package com.manning.ch04.logs;

public class LogAnalyzer2 {
	private WebService service;
	private EmailService email;

	public LogAnalyzer2(WebService service, EmailService email) {
		this.service = service;
		this.email = email;
	}

	public void analyze(String fileName) {
		if (fileName.length() < 8) {
			try {
				service.logError("Filename too short:" + fileName);
			} catch (Exception e) {
				email.sendEmail("admin@logan.com", "Failure", e.getMessage());
			}
		}
	}
}
