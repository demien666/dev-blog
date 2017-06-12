package com.demien.amq;

import org.apache.activemq.command.ActiveMQObjectMessage;

import javax.jms.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Consumer<T extends Serializable> implements MessageListener {

    private Connection connection;
    private Session session;
    private MessageConsumer consumer;
    private String consumerId="NULL";

    private List<T> messages = new ArrayList<T>();

    public Consumer(ConnectionFactory factory, String queueName) throws JMSException {
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(queueName);
        consumer = session.createConsumer(destination);
        consumer.setMessageListener(this);
    }

    public List<T> getMessages() {
        return messages;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId=consumerId;
    }

    public void onMessage(Message message) {
        try {
            if (message instanceof ObjectMessage) {
                messages.add((T)((ActiveMQObjectMessage) message).getObject());
                System.out.println("Consumer["+consumerId+"] Message received:");
            } else {
                System.out.println("Consumer["+consumerId+"] Invalid message received.");
            }
        } catch (Exception e) {
            System.out.println("Caught:" + e);
            e.printStackTrace();
        }
    }
}