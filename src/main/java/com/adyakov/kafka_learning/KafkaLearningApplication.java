package com.adyakov.kafka_learning;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class KafkaLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaLearningApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
		return args -> {
			for (int i = 0; i < 100; i++) {

				kafkaTemplate.send("mykafka", "Hello awesome kafka " + i);
			}
		};
	}

}
