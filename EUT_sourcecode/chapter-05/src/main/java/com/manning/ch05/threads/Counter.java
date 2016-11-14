package com.manning.ch05.threads;

import java.util.concurrent.atomic.AtomicLong;

public class Counter {

	private AtomicLong value = new AtomicLong();

	public long getAndIncrement() {
		return value.getAndIncrement();
	}
}
