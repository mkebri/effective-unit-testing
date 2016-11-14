package com.manning.ch05.template;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Template {

	private Map<String, String> data;
	private String templateText;

	public Template(String templateText) {
		if (templateText == null) {
			throw new NullPointerException("Template text must not be null");
		}
		this.data = new HashMap<String, String>();
		this.templateText = templateText;
	}

	public void setValueForPlaceholder(String name, String value) {
		this.data.put(name, value);
	}

	public String evaluate() {
		String result = templateText;
		Iterator<Map.Entry<String, String>> entries = replacements();
		while (entries.hasNext()) {
			Map.Entry<String, String> entry = entries.next();
			String regex = "\\$\\{" + entry.getKey() + "\\}";
			String value = (String) entry.getValue();
			result = result.replaceAll(regex, value);
		}
		if (result.matches(".*(\\$\\{\\w+\\}).*")) {
			throw new IllegalStateException("Not all placeholders were replaced!");
		}
		return result;
	}

	private Iterator<Map.Entry<String, String>> replacements() {
		return data.entrySet().iterator();
	}

}
