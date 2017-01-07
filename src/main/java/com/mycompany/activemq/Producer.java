/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.activemq;

import com.mycompany.activemq.constants.ActiveMQConnectionSettings;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.Random;

public class Producer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    public static void main(String[] args) throws JMSException {

        // initialise a connection factory for establishing connections to the broker
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionSettings.buildActiveMQConnectionURL());

        // establish the connection to the ActiveMQ broker
        Connection connection = activeMQConnectionFactory.createConnection();

        // start the connection
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // create destination (queue)
        Queue queue = session.createQueue("test_queue");

        MessageProducer messageProducer = session.createProducer(queue);

        while (true) {
            // create a message to send
            TextMessage textMessage = session.createTextMessage("Generated random number: " + new Random().nextInt());

            // send the message
            LOGGER.info(Producer.class.getName() + " - Sending message: {}", textMessage.getText());
            messageProducer.send(textMessage);
        }

        //connection.stop();

        //System.exit(0);
    }
}
