package com.epam.training.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static final Logger LOGGER =
            LogManager.getLogger(PropertyReader.class);

    private static final String PROPERTY_EXTENSION = ".properties";

    private final Properties properties;

    /**
     * Loads properties from properties file.
     * @param fileName properties file name
     */
    public PropertyReader(final String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream input = classLoader
                                    .getResourceAsStream(
                                    fileName + PROPERTY_EXTENSION)) {
            properties = new Properties();
            properties.load(input);
        } catch (IOException ex) {
            LOGGER.error(ex);
            throw new IllegalArgumentException(ex);
        }
    }

    /**
     * Gets property value.
     * @param key property key
     * @return property value
     */
    public String getProperty(final String key) {
        return properties.getProperty(key);
    }

}
