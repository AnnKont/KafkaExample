package com.ann.kont.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaListenerService {
    @KafkaListener(topics = "${app.kafka.messageTopic}")
    public void listen(String text){
        log.info("Successfully get message: {}", text);
    }
}
