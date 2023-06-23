package com.example.kafkaexample;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "example", groupId = "foo")
    void exampleListener(String data) {
        System.out.println("Example listener received: " + data);
    }

    @KafkaListener(topics = "topic2", groupId = "foo")
    void secondListener(String data) {
        System.out.println("Topic2 listener received: " + data);
    }

}
