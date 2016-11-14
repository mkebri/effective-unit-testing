package com.manning.ch06.platform;

/**
 * Platform API, implemented by concrete classes Platform.MacOsX and
 * Platform.Windows.
 */
public abstract class Platform {

	public static Platform current() {
		String osName = System.getProperty("os.name");
		if (osName.contains("OS X")) {
			return new MacOsX();
		}
		return new Windows();
	}

	public boolean isMac() {
		return false;
	}

	public boolean isWindows() {
		return false;
	}

	public abstract String downloadDir();

	/**
	 * Mac OS X implementation
	 */
	public static class MacOsX extends Platform {
		@Override
		public boolean isMac() {
			return true;
		}

		@Override
		public String downloadDir() {
			return System.getProperty("user.home") + "/Downloads";
		}
	}

	/**
	 * Windows implementation
	 */
	public static class Windows extends Platform {
		@Override
		public boolean isWindows() {
			return true;
		}

		@Override
		public String downloadDir() {
			return "c:\\users\\" + System.getProperty("user.name") + "\\downloads";
		}
	}
}
