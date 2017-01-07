/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Producer {

    private static final String ACTIVEMQ_URL = "tcp://localhost";
    private static final int ACTIVEMQ_PORT = 61616; //default port

    public static void main(String[] args) throws JMSException {

        // initialise a connection factory for establishing connections to the broker
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL + ":" + ACTIVEMQ_PORT);

        // establish the connection to the ActiveMQ broker
        Connection connection = activeMQConnectionFactory.createConnection();

        // start the connection
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // create destination (queue)
        Queue queue = session.createQueue("test_queue");

        MessageProducer messageProducer = session.createProducer(queue);

        // create a message to send
        TextMessage textMessage = session.createTextMessage("Hello, World!");

        // send the message
        messageProducer.send(textMessage);

        connection.stop();

        System.exit(0);
    }
}
