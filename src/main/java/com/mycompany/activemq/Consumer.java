/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Consumer {

    private static final String ACTIVEMQ_URL = "tcp://localhost";
    private static final int ACTIVEMQ_PORT = 61616; //default port

    public static void main(String[] args) throws JMSException {

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL + ":" + ACTIVEMQ_PORT);

        Connection connection = activeMQConnectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("test_queue");

        MessageConsumer messageConsumer = session.createConsumer(queue);

        int messages = 0;
        while (messages < 10) {
            TextMessage textMessage = (TextMessage) messageConsumer.receive();
            System.out.println("message #" + messages + ":" + textMessage.getText());
            messages++;
        }

        connection.stop();

        System.exit(0);
    }
}
