/*
 * |-------------------------------------------------
 * | Copyright © 2017 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.activemq.constants;

import com.mycompany.activemq.util.PropertyLoader;

public final class ActiveMQConnectionSettings {

    private static final String ACTIVEMQ_URL_PROPERTY_KEY = "activemq.url";
    private static final String ACTIVEMQ_PORT_PROPERTY_KEY = "activemq.port";
    private static final String ACTIVEMQ_QUEUE_PROPERTY_KEY = "activemq.queue";

    private ActiveMQConnectionSettings() {}

    public static String buildActiveMQConnectionURL() {
        String activeMQUrl = PropertyLoader.loadProperty(ACTIVEMQ_URL_PROPERTY_KEY);
        String activeMqPort = PropertyLoader.loadProperty(ACTIVEMQ_PORT_PROPERTY_KEY);
        return activeMQUrl + ":" + activeMqPort;
    }

    public static String getActiveMQQueueDestination() {
        return PropertyLoader.loadProperty(ACTIVEMQ_QUEUE_PROPERTY_KEY);
    }
}
