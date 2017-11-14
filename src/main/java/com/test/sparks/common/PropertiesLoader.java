package com.test.sparks.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public interface PropertiesLoader extends ResourceProperties {

	default Optional<Properties> loadPropertiesFromFile(String fileName) {
		Optional<Properties> result = Optional.empty();
		Properties properties = new Properties();
		InputStream input = null;
		try {
			input = ResourceProperties.CLASS_LOADER.getResourceAsStream(fileName);
			properties.load(input);
			result = Optional.of(properties);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
