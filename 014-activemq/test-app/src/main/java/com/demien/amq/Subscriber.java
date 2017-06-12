package com.demien.amq;

import org.apache.activemq.command.ActiveMQObjectMessage;

import javax.jms.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Subscriber<T extends Serializable> implements MessageListener {

    private Connection connection;
    private Session session;
    private MessageConsumer consumer;
    private String subscriberId="";

    private List<T> messages = new ArrayList<T>();

    public Subscriber(ConnectionFactory factory, String topicName) throws JMSException {
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(topicName);
        consumer = session.createConsumer(topic);
        consumer.setMessageListener(this);
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId=subscriberId;
    }

    public List<T> getMessages() {
        return messages;
    }

    public void onMessage(Message message) {
        try {
            if (message instanceof ObjectMessage) {
                System.out.println("Subscriber["+subscriberId+"] received message.");
                messages.add((T)((ActiveMQObjectMessage) message).getObject());
            } else {
                System.out.println("Invalid message received.");
            }
        } catch (Exception e) {
            System.out.println("Caught:" + e);
            e.printStackTrace();
        }
    }
}