package com.demien.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;
import java.util.function.BiConsumer;

public class MessageConsumer<K, V> {
    private final String topic;
    private final String groupId;
    private final long startingOffset;
    private final KafkaConsumer<K, V> kafkaConsumer;

    public MessageConsumer(String topic, String groupId) {
        this(topic, groupId, -1);
    }

    /**
     * @param topic          - id of topic
     * @param groupId        - id of consumer group
     * @param startingOffset - offset to read messages. 0 - from the beginning.
     *                       -1 - from the end. other values - start reading from this value
     */
    public MessageConsumer(String topic, String groupId, long startingOffset) {
        this.topic = topic;
        this.groupId = groupId;
        this.startingOffset = startingOffset;

        Properties configProperties = new Properties();
        configProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        configProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        configProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, "testClient");
        configProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        configProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        kafkaConsumer = new KafkaConsumer<>(configProperties);

        kafkaConsumer.subscribe(Arrays.asList(topic), new ConsumerRebalanceListener() {
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                System.out.printf("%s topic-partitions are revoked from this consumer\n", Arrays.toString(partitions.toArray()));
            }

            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                System.out.printf("%s topic-partitions are assigned to this consumer\n", Arrays.toString(partitions.toArray()));
                Iterator<TopicPartition> topicPartitionIterator = partitions.iterator();
                while (topicPartitionIterator.hasNext()) {
                    TopicPartition topicPartition = topicPartitionIterator.next();
                    System.out.println("Current offset is " + kafkaConsumer.position(topicPartition) + " committed offset is ->" + kafkaConsumer.committed(topicPartition));
                    if (MessageConsumer.this.startingOffset == 0) {
                        System.out.println("Setting offset to begining");

                        kafkaConsumer.seekToBeginning(topicPartition);
                    } else if (MessageConsumer.this.startingOffset == -1) {
                        System.out.println("Setting it to the end ");

                        kafkaConsumer.seekToEnd(topicPartition);
                    } else {
                        System.out.println("Resetting offset to " + MessageConsumer.this.startingOffset);
                        kafkaConsumer.seek(topicPartition, MessageConsumer.this.startingOffset);
                    }
                }
            }
        });

    }

    public void startReceiving(BiConsumer<K, V> biConsumer) {
        try {
            while (true) {
                ConsumerRecords<K, V> records = kafkaConsumer.poll(100);
                records.forEach(record -> biConsumer.accept(record.key(), record.value()));
                if (startingOffset == -2) kafkaConsumer.commitSync();
            }
        } finally {
            kafkaConsumer.close();
        }
    }

    public static void main(String[] args) {
        final MessageConsumer<String, String> testConsumer = new MessageConsumer<>("test0", "testGroup", 2);
        testConsumer.startReceiving((k, v) -> System.out.println("received:" + v));
    }
}
