/*
 * |-------------------------------------------------
 * | Copyright © 2017 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.activemq.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyLoader.class);

    private static final String ACTIVEMQ_PROPERTY_FILENAME = "activemq.properties";

    public static String loadProperty(String propertyKey) {
        Properties properties = new Properties();

        String propertValue = "";
        try (InputStream inputStream = PropertyLoader.class.getClassLoader().getResourceAsStream(ACTIVEMQ_PROPERTY_FILENAME)) {

            if (inputStream == null) {
                String errorMesage = "Unable to load property value for: {}";
                LOGGER.error(errorMesage, propertyKey);
                throw new IllegalStateException(errorMesage);
            }

            properties.load(inputStream);

            propertValue = properties.getProperty(propertyKey);

        } catch (IOException e) {
            LOGGER.error("{}", e);
        }

        return propertValue;
    }

}
