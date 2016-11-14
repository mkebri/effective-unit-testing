package com.manning.ch06.platform;

import java.io.File;

public class Configuration {

	public static File downloadDir() {
		return new File(Platform.current().downloadDir());
	}

}
