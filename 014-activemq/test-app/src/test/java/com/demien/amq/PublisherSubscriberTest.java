package com.demien.amq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class PublisherSubscriberTest {
    public static String brokerURL = "tcp://localhost:61616";
    private final ConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);
    private final String topicName="TestTopic";

    private final int SUBSCRIBERS_COUNT=10;

    Publisher<TestObject> publisher;
    List<Subscriber<TestObject>> subscribers;

    @Before
    public void init() throws JMSException {
        publisher=new Publisher<TestObject>(factory, topicName);

        subscribers=new ArrayList<Subscriber<TestObject>>();
        for (int i=0; i<SUBSCRIBERS_COUNT; i++) {
            Subscriber<TestObject> subscriber=new Subscriber<TestObject>(factory, topicName);
            subscriber.setSubscriberId(Integer.toString(i));
            subscribers.add(subscriber);
        }
    }

    @After
    public void finish() throws JMSException {
        publisher.close();
    }

    @Test
    public void multipleMessagesTest() throws JMSException, InterruptedException {
        Long TEST_ID=-1L;
        String TEST_NAME="test name";


        TestObject testObject=new TestObject();
        testObject.id=new Long(TEST_ID);
        testObject.name=new String(TEST_NAME);

        //post message
        publisher.postObjectMessage(testObject);
        Thread.sleep(1000);

        // receive messages
        for (Subscriber<TestObject> subscriber:subscribers) {
            TestObject received=subscriber.getMessages().get(0);
            assertTrue(received.name.equals(TEST_NAME));
            assertTrue(received.id.equals(TEST_ID));
        }

    }


}
