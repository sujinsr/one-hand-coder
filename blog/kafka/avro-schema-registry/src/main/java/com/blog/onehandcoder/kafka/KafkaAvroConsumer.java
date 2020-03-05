package com.blog.onehandcoder.kafka;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class KafkaAvroConsumer {

    public static void main(String[] args) {
        String topicName = "customer-topic";

        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        properties.setProperty("schema.registry.url", "http://locahost:8081");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "customer-topic-group");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        Consumer<String, Customer> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList(topicName));

        while (true) {
            ConsumerRecords<String, Customer> consumerRecords = consumer.poll(Duration.ofMillis(1));
            consumerRecords.forEach(consumerRecord -> {
                System.out.println("Key -> " + consumerRecord.key());
                System.out.println("CustomerRecord -> " + consumerRecord.value());
            });
            consumer.commitSync();
        }
    }
}
