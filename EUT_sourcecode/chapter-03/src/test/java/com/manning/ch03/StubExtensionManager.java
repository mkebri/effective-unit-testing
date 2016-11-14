/**
 * 
 */
package com.manning.ch03;

import com.manning.ch03.ExtensionManager;

public class StubExtensionManager implements ExtensionManager {
	public boolean shouldExtensionsBeValid;

	public boolean isValid(String fileName) {
		return shouldExtensionsBeValid;
	}
}