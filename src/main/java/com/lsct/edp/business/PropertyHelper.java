package com.lsct.edp.business;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.naming.ConfigurationException;

public class PropertyHelper {

	public static final String CONFIG_DEFAULT_FILE_NAME = "config.properties";
	public static final String CONFIG_PRICE_ROUND = "PRICE_ROUND";
	public static final String CONFIG_LIST_SEPARATOR = ",";

	private static Map<String, Properties> settings = new HashMap<String, Properties>();

	public static Properties gerPropertyReader(String filename) throws ConfigurationException {
		// if property reader already created, return it
		if (settings.containsKey(filename))
			return settings.get(filename);

		// try to create the property looking for the file in the context resource
		// folder
		Properties propertyReader = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try (InputStream resourceStream = loader.getResourceAsStream(filename)) {
			propertyReader.load(resourceStream);
		} catch (IOException e) {
			throw new ConfigurationException(String.format("File %s not found in resource folder: %s", filename, e.getMessage()));
		}

		// put new property into the settings map for reuse
		settings.put(filename, propertyReader);
		return propertyReader;
	}

	public static String getRequiredProperty(Properties propReader, String property) throws ConfigurationException {
		String propValue = propReader.getProperty(property);
		if (propValue == null || propValue.isEmpty()) {
			throw new ConfigurationException("Property " + property + " is missing or empty");
		}
		return propValue;
	}

	public static double getRequiredPropertyAsDouble(Properties propReader, String property) throws ConfigurationException {
		String propValue = getRequiredProperty(propReader, property);
		try {// try cast the string value to the generic class
			return Double.parseDouble(propValue);
		} catch (NumberFormatException e) {
			throw new ConfigurationException(String.format("Property %s not found or in bad format", property));
		}
	}
}
