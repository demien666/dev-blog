package com.demien.amq.jbehave;

import com.demien.amq.Publisher;
import com.demien.amq.Subscriber;
import com.demien.amq.TestObject;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by dmitry on 09.02.15.
 */
public class PublisherSubscriberJB {
    public static String brokerURL = "tcp://localhost:61616";
    private final ConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);
    private final String topicName="TestTopic";

    private final int SUBSCRIBERS_COUNT=10;
    Long TEST_ID=-1L;
    String TEST_NAME="test name";

    Publisher<TestObject> publisher;
    List<Subscriber<TestObject>> subscribers;

/*    (PublisherSubscriber.story)
    Scenario: broadcast message receiving by all subscribers of topic
    Given one publisher, several subscribers, one message (PENDING)
    When publisher sends message (PENDING)
    Then all subscribers have to receive this message (PENDING)
*/
    @Given("one publisher, several subscribers, one message")
    public void givenOnePublisherSeveralSubscribersOneMessage() throws JMSException {
        publisher=new Publisher<TestObject>(factory, topicName);

        subscribers=new ArrayList<Subscriber<TestObject>>();
        for (int i=0; i<SUBSCRIBERS_COUNT; i++) {
            Subscriber<TestObject> subscriber=new Subscriber<TestObject>(factory, topicName);
            subscriber.setSubscriberId(Integer.toString(i));
            subscribers.add(subscriber);
        }
    }

    @When("publisher sends message")
    public void whenPublisherSendsMessage() throws JMSException, InterruptedException {

        TestObject testObject=new TestObject();
        testObject.id=new Long(TEST_ID);
        testObject.name=new String(TEST_NAME);

        //post message
        publisher.postObjectMessage(testObject);
        Thread.sleep(1000);
    }

    @Then("all subscribers have to receive this message")
    public void thenAllSubscribersHaveToReceiveThisMessage() {
        for (Subscriber<TestObject> subscriber:subscribers) {
            TestObject received=subscriber.getMessages().get(0);
            assertTrue(received.name.equals(TEST_NAME));
            assertTrue(received.id.equals(TEST_ID));
        }
    }

}
