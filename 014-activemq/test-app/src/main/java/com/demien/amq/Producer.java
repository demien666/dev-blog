package com.demien.amq;

import javax.jms.*;
import java.io.Serializable;

public class Producer<T extends Serializable> {
    private Connection connection;
    private Session session;
    private MessageProducer producer;

    public Producer(ConnectionFactory factory, String queueName) throws JMSException {
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(queueName);
        producer = session.createProducer(destination);
    }

    public void postObjectMessage(T object) throws JMSException {
        Message message = session.createObjectMessage(object);
        producer.send(message);
        System.out.println("Sending message:"+object.toString());
    }

    public void close() throws JMSException {
        if (connection != null) {
            connection.close();
        }
    }
}
