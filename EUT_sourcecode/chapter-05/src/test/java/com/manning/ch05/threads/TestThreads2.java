package com.manning.ch05.threads;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

public class TestThreads2 {
	private final int threads = 10;
	private final int callsPerThread = 100;
	private Counter counter;

	@Before
	public void setUp() throws Exception {
		counter = new Counter();
	}

	@Test
	public void concurrentAccessFromMultipleThreads() throws Exception {
		final Set<Long> values = new HashSet<Long>();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < callsPerThread; i++) {
					values.add(counter.getAndIncrement());
				}
			}
		};
		ExecutorService threadPool = Executors.newFixedThreadPool(threads * 5);
		for (int i = 0; i < threads; i++) {
			threadPool.submit(runnable);
		}
		threadPool.awaitTermination(10, TimeUnit.SECONDS);
		int expectedNoOfValues = threads * callsPerThread;
		assertEquals(expectedNoOfValues, values.size());
	}
}
