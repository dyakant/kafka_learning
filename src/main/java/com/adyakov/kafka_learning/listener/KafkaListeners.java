package com.adyakov.kafka_learning.listener;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.adyakov.kafka_learning.config.KafkaTopicConfig.TOPIC_NAME;

@Slf4j
@Component
public class KafkaListeners {

    @SneakyThrows
    @KafkaListener(topics = TOPIC_NAME)
    void listener(String data) {
        log.info("KafkaListener, receives: {}", data);
        log.info("Start processing...");
        for (int i = 1; i <= 5; i++) {
            Thread.sleep(1000);
            System.out.println(i + " second");
        }
        log.info("Stop processing");
    }
}
