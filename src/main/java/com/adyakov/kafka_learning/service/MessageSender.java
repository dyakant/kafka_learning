package com.adyakov.kafka_learning.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

import static com.adyakov.kafka_learning.config.KafkaTopicConfig.TOPIC_NAME;

/**
 * Created by Anton Dyakov on 03.04.2023
 */
@Slf4j
@Service
@AllArgsConstructor
public class MessageSender {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public boolean sendMessage(String message){
        var future = kafkaTemplate.send(TOPIC_NAME, message);

        try {
            var result = future.get();
            log.info("Successfully send to \"{}\" with offset {} to partition {}",
                    result.getProducerRecord().topic(), result.getRecordMetadata().offset(),
                    result.getRecordMetadata().partition());
        } catch (ExecutionException | InterruptedException e) {
            log.error("Cannot send message to Kafka Topic \"{}\"", TOPIC_NAME, e);
            return false;
        }
        return true;
    }
}
