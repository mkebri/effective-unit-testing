package com.manning.ch06.platform;

import static com.manning.ch06.platform.RegexMatcher.matches;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TestConfiguration1 {

	@Test
	public void knowsTheSystemsDownloadDirectory() throws Exception {
		String dir = Configuration.downloadDir().getAbsolutePath();
		Platform platform = Platform.current();
		if (platform.isMac()) {
			assertThat(dir, matches("/Users/(.*?)/Downloads"));
		} else if (platform.isWindows()) {
			assertThat(dir.toLowerCase(),
					matches("c:\\\\users\\\\(.*?)\\\\downloads"));
		}
	}
}
