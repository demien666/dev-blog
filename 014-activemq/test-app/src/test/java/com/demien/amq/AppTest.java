package com.demien.amq;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import java.util.List;
import static org.junit.Assert.*;

public class AppTest  {

    public static String brokerURL = "tcp://localhost:61616";
    private final ConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);
    private final String queueName="TestQueue";

    Producer producer;
    Consumer consumer;

    @Before
    public void init() throws JMSException {
        producer=new Producer<TestObject>(factory, queueName);
        consumer=new Consumer<TestObject>(factory, queueName);
    }

    @After
    public void finish() throws JMSException {
        producer.close();
    }

    @Test
    public void singleMessageTest() throws JMSException, InterruptedException {
        Long TEST_ID=-1L;
        String TEST_NAME="test name";

        TestObject testObject=new TestObject();
        testObject.id=new Long(TEST_ID);
        testObject.name=new String(TEST_NAME);

        //post message
        producer.postObjectMessage(testObject);
        Thread.sleep(1000);

        //receive message
        List<TestObject> messages=consumer.getMessages();
        assertTrue(messages.size()>0);
        TestObject received=messages.get(0);
        assertTrue(received.name.equals(TEST_NAME));
        assertTrue(received.id.equals(TEST_ID));
    }
}
