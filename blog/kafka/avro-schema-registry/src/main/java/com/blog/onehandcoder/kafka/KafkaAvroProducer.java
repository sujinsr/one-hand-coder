package com.blog.onehandcoder.kafka;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaAvroProducer {

    public static void main(String[] args) {
        String topicName = "customer-topic";

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        properties.setProperty("schema.registry.url", "http://localhost:8081");

        Producer<String, Customer> producer = new KafkaProducer<>(properties);

        String key = "KeyJohn";
        Customer customer = new Customer("John", 22);

        ProducerRecord<String, Customer> producerRecord = new ProducerRecord<>(topicName, key, customer);

        Callback onCompletionCallback = new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e == null) {
                    System.out.println("Message sent successfully, " + recordMetadata);
                } else {
                    System.out.println("Failed to sent message");
                    e.printStackTrace();
                }
            }
        };

        producer.send(producerRecord, onCompletionCallback);
        producer.close();
    }

}
