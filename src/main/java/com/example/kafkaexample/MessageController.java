package com.example.kafkaexample;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/v1/messages")
public class MessageController {

    private KafkaTemplate<String, String> kafkaTemplate;

    public MessageController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    @RequestMapping("/topic2")
    public void publish(@RequestBody MessageRequest request) {
        CompletableFuture<SendResult<String, String>> future =
                kafkaTemplate.send("topic2", request.message());

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + request.message() +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        request.message() + "] due to : " + ex.getMessage());
            }
        });
    }
}
