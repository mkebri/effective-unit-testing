package com.manning.ch04.logs;

import com.manning.ch04.logs.WebService;

public class MockWebService implements WebService {
	public String lastError;
	@Override
	public void logError(String message) {
		lastError = message;
	}
}
