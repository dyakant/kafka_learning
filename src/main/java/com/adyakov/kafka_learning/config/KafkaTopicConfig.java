package com.adyakov.kafka_learning.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    public static final String TOPIC_NAME = "mykafka";

    @Bean
    public NewTopic myKafkaTopic() {
        return TopicBuilder.name(TOPIC_NAME)
                .replicas(1)
                .partitions(10)  // указывается сразу, потом сложно
                .build();
    }
}
