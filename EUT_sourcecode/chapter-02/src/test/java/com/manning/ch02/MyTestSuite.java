package com.manning.ch02;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ LogAnalyzerTest.class, SampleOfRunningMultipleTests.class })
public class MyTestSuite {
}
