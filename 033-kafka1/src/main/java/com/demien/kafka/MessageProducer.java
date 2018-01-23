package com.demien.kafka;

import org.apache.kafka.clients.producer.*;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class MessageProducer<K, V> {

    private final Producer kafkaProducer;
    private final String topicName;
    private final Consumer<RecordMetadata> messageSentCallback;

    public MessageProducer(String topicName) {
        this(topicName, null);
    }

    public MessageProducer(String topicName, Consumer<RecordMetadata> messageSentCallback) {
        Properties configProperties = new Properties();
        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer");
        configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        this.kafkaProducer = new KafkaProducer(configProperties);
        this.topicName = topicName;
        this.messageSentCallback = messageSentCallback;
    }


    public void sendMessage(K key, V value) {
        ProducerRecord<K, V> rec = new ProducerRecord<K, V>(topicName, key, value);
        Future<RecordMetadata> future = kafkaProducer.send(rec);
        if (messageSentCallback != null) {
            CompletableFuture.supplyAsync(() -> {
                try {
                    RecordMetadata recordMetadata = future.get();
                    messageSentCallback.accept(recordMetadata);
                } catch (Exception e) {
                }
                return null;
            });
        }
    }

    public void close() {
        kafkaProducer.close();
    }


    public static void main(String[] args) throws InterruptedException {
        MessageProducer<String, String> testProducer = new MessageProducer<String, String>("test0", (recordMetadata) -> {
            System.out.println("Message was sent: offset:" + recordMetadata.offset() + " partition:" + recordMetadata.partition() + " topic:" + recordMetadata.topic());
        });
        testProducer.sendMessage(null, "Test 1 " + new Date().toString());
        testProducer.sendMessage(null, "Test 2 " + new Date().toString());
        testProducer.sendMessage(null, "Test 3 " + new Date().toString());
        testProducer.close();
    }


}
