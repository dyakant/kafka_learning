package com.adyakov.kafka_learning.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(
            topics = "mykafka",
            groupId = "groupId"
    )
    void listener(String data) {
        System.out.println("Listener receives: " + data);
    }
}
