package com.manning.ch04.logs;

import static org.junit.Assert.*;

import org.junit.Test;

import com.manning.ch04.logs.EmailService;
import com.manning.ch04.logs.LogAnalyzer2;
import com.manning.ch04.logs.WebService;

public class LogAnalyzer2Test {
	private static final String TOO_SHORT_FILE_NAME = "abc.ext";

	@Test
	public void fallBackToSendingEmailWhenWebServiceFails() {
		Exception simulatedException = new Exception("Simulated exception");
		WebService webService = new FailingWebService(simulatedException);
		MockEmailService emailService = new MockEmailService();
		LogAnalyzer2 log = new LogAnalyzer2(webService, emailService);
		log.analyze(TOO_SHORT_FILE_NAME);
		assertEquals("admin@logan.com", emailService.recipient);
		assertEquals(simulatedException.getMessage(), emailService.body);
		assertEquals("Failure", emailService.subject);
	}

	private class FailingWebService implements WebService {
		public Exception exceptionToThrow;

		public FailingWebService(Exception exception) {
			this.exceptionToThrow = exception;
		}

		public void logError(String message) throws Exception {
			throw exceptionToThrow;
		}
	}

	private class MockEmailService implements EmailService {
		public String recipient;
		public String subject;
		public String body;

		public void sendEmail(String recipient, String subject, String body) {
			this.recipient = recipient;
			this.subject = subject;
			this.body = body;
		}
	}
}