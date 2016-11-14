package com.manning.ch06.platform;

import static com.manning.ch06.platform.RegexMatcher.matches;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TestConfiguration3 {

	@Test
	public void knowsTheSystemsDownloadDirectoryOnMacOsX() throws Exception {
		String downloadsDir = new Platform.MacOsX().downloadDir();
		assertThat(downloadsDir, matches("/Users/(.*?)/Downloads"));
	}

	@Test
	public void knowsTheSystemsDownloadDirectoryOnWindows() throws Exception {
		String downloadsDir = new Platform.Windows().downloadDir();
		assertThat(downloadsDir.toLowerCase(),
				matches("c:\\\\users\\\\(.*?)\\\\downloads"));
	}
}
