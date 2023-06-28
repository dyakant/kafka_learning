package com.adyakov.kafka_learning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

import static com.adyakov.kafka_learning.config.KafkaTopicConfig.TOPIC_NAME;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

/**
 * Created by Anton Dyakov on 31.05.2023
 */
@SpringBootTest
@Testcontainers
public class KafkaListenerTest {

    @Container
    static final KafkaContainer kafka = new KafkaContainer(
            DockerImageName.parse("confluentinc/cp-kafka:7.3.3")
    );

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    void simpleKafkaTest() {
        String message = "Hello from test";
        kafkaTemplate.send(TOPIC_NAME, message);
        await()
                .pollInterval(Duration.ofSeconds(1))
                .atMost(10, SECONDS);
//                .untilAsserted(() -> {
//                    Optional<Product> optionalProduct = productRepository.findByCode("P100");
//                    assertThat(optionalProduct).isPresent();
//                    assertThat(optionalProduct.get().getCode()).isEqualTo("P100");
//                    assertThat(optionalProduct.get().getPrice()).isEqualTo(new BigDecimal("14.50"));
//                });
    }
}
