package com.demien.amq;

import javax.jms.*;
import java.io.Serializable;

public class Publisher<T extends Serializable> {
    private Connection connection;
    private Session session;
    private MessageProducer publisher;

    public Publisher(ConnectionFactory factory, String topicName) throws JMSException {
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(topicName);
        publisher = session.createProducer(topic);
    }

    public void postObjectMessage(T object) throws JMSException {
        Message message = session.createObjectMessage(object);
        publisher.send(message);
    }

    public void close() throws JMSException {
        if (connection != null) {
            connection.close();
        }
    }
}
