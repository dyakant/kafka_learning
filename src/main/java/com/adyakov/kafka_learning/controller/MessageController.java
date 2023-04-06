package com.adyakov.kafka_learning.controller;

import com.adyakov.kafka_learning.service.MessageSender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/messages")
public class MessageController {

    private final MessageSender messageSender;

    @PostMapping("/send")
    public ResponseEntity<String> publish(@RequestBody MessageRequest request) {
        if (messageSender.sendMessage(request.message())){
            return ResponseEntity.ok("send successfully");
        }
        return ResponseEntity.internalServerError().body("error, has not been sent");
    }
}
