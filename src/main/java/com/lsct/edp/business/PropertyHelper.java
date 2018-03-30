package com.lsct.edp.business;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.naming.ConfigurationException;

/**
 * This class provide a list of static methods to help reading values from a property file
 * @author Andrea Boggia
 * @author andrea.boggia@gmail.com
 * @version 0.0.1
 */
public class PropertyHelper {
	/**
	 * Property key used to get default filename
	 */
	public static final String CONFIG_DEFAULT_FILE_NAME = "config.properties";
	/**
	 * Property key used to get the price round valuexs
	 */
	public static final String CONFIG_PRICE_ROUND = "PRICE_ROUND";
	/**
	 * Property key used to get the price round precision value
	 */
	public static final String CONFIG_LIST_SEPARATOR = ",";
	/**
	 * Map to store and reuse created tax calculator 
	 */
	private static Map<String, Properties> settings = new HashMap<String, Properties>();
	
	/**
	 * return the property reader for fiven filename.
	 * @param filename resource filename. It's assumed that file is locate into the resource file
	 * @return the property reader for the given file
	 * @throws ConfigurationException in case the file is missing
	 */
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

	/**
	 * Searches for the property with the specified key in the specified property.
	 * @param propReader property list
	 * @param property property key
	 * @return a string representing property value
	 * @throws ConfigurationException in case property key is not found or with empty value
	 */
	public static String getRequiredProperty(Properties propReader, String property) throws ConfigurationException {
		String propValue = propReader.getProperty(property);
		if (propValue == null || propValue.isEmpty()) {
			throw new ConfigurationException("Property " + property + " is missing or empty");
		}
		return propValue;
	}

	/**
	 * Searches for the property with the specified key in the specified property 
	 * @param propReader property list
	 * @param property property key
	 * @return property value as double
	 * @throws ConfigurationException in case property key is not found, with empty value or not parsable as double
	 */
	public static double getRequiredPropertyAsDouble(Properties propReader, String property) throws ConfigurationException {
		String propValue = getRequiredProperty(propReader, property);
		try {// try cast the string value to the generic class
			return Double.parseDouble(propValue);
		} catch (NumberFormatException e) {
			throw new ConfigurationException(String.format("Property %s not found or in bad format", property));
		}
	}
}
