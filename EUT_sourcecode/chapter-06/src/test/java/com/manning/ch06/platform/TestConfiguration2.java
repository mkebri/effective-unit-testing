package com.manning.ch06.platform;

import static com.manning.ch06.platform.RegexMatcher.matches;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeTrue;

import org.junit.Before;
import org.junit.Test;

public class TestConfiguration2 {

	Platform platform;
	String downloadsDir;

	@Before
	public void setUp() {
		platform = Platform.current();
		downloadsDir = Configuration.downloadDir().getAbsolutePath();
	}

	@Test
	public void knowsTheSystemsDownloadDirectoryOnMacOsX() throws Exception {
		assumeTrue(platform.isMac());
		assertThat(downloadsDir, matches("/Users/(.*?)/Downloads"));
	}

	@Test
	public void knowsTheSystemsDownloadDirectoryOnWindows() throws Exception {
		assumeTrue(platform.isWindows());
		assertThat(downloadsDir.toLowerCase(),
				matches("c:\\\\users\\\\(.*?)\\\\downloads"));
	}
}
