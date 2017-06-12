package com.demien.amq.jbehave;

import com.demien.amq.Consumer;
import com.demien.amq.Producer;
import com.demien.amq.TestObject;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Created by dmitry on 14.02.15.
 */
public class ProducerConsumerJB {

    public static String brokerURL = "tcp://localhost:61616";
    private final ConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);
    private final String queueName1="TestQueue1";
    private final String queueName2="TestQueue2";

    Producer<TestObject> producer;
    Consumer<TestObject> consumer;
    List<Consumer<TestObject>> consumers;
/*
    Scenario: simple message receiving by one consumer
    Given one producer, one consumer, one message (PENDING)
    When producer sends message to queue (PENDING)
    Then consumer receives message from queue (PENDING)
*/
    @Given("one producer, one consumer, one message")
    public void givenOneProducerOneConsumerOneMessage() throws JMSException {
        producer=new Producer<TestObject>(factory, queueName1);
        consumer=new Consumer<TestObject>(factory, queueName1);
        consumer.setConsumerId("THE_ONLY_CONSUMER");
    }

    @When("producer sends message to queue")
    public void whenProducerSendsMessageToQueue() throws JMSException, InterruptedException {
        producer.postObjectMessage(new TestObject());
        Thread.sleep(1000);
    }

    @Then("consumer receives message from queue")
    public void thenConsumerReceivesMessageFromQueue() {
        int receivedMessageCount=consumer.getMessages().size();
        assertEquals(1, receivedMessageCount);
    }

/*
    Scenario: simple message receiving by several consumers:only one of them have to receive message
    Given one producer, several consumers, one message (PENDING)
    When producer sends message to queue (PENDING)
    Then only one consumer have to receive message from queue (PENDING)
*/
    @Given("one producer, several consumers, one message")
    public void givenOneProducerSeveralConsumersOneMessage() throws JMSException {
        producer=new Producer<TestObject>(factory, queueName2);
        consumers=new ArrayList<Consumer<TestObject>>();
        for (int i=0;i<10;i++) {
            Consumer<TestObject> eachConsumer=new Consumer<TestObject>(factory, queueName2);
            eachConsumer.setConsumerId("Consumer#"+Integer.toString(i));
            consumers.add(eachConsumer);
        }
    }
/* - already implemented
    @When("producer sends message to queue")
    public void whenProducerSendsMessageToQueue() {
        // PENDING
    }
*/
    @Then("only one consumer have to receive message from queue")
    public void thenOnlyOneConsumerHaveToReceiveMessageFromQueue() {
        int receivedMessageCount=0;
        for (Consumer<TestObject> eachConsumer: consumers) {
            receivedMessageCount=receivedMessageCount+eachConsumer.getMessages().size();
        }
        assertEquals(1, receivedMessageCount);
    }
}
