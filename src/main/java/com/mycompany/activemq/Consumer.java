/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.activemq;

import com.mycompany.activemq.constants.ActiveMQConnectionSettings;
import com.mycompany.activemq.util.PropertyLoader;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Consumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    public static void main(String[] args) throws JMSException {

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionSettings.buildActiveMQConnectionURL());

        Connection connection = activeMQConnectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("test_queue");

        MessageConsumer messageConsumer = session.createConsumer(queue);

        int messages = 0;
        while (messages < 10) {
            TextMessage textMessage = (TextMessage) messageConsumer.receive();
            LOGGER.info("message #" + messages + ":" + textMessage.getText());
            messages++;
        }

        connection.stop();

        System.exit(0);
    }
}
