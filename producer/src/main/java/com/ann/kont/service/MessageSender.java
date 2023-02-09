package com.ann.kont.service;

import com.ann.kont.config.KafkaProperties;
import com.ann.kont.model.Message;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageSender {
    KafkaTemplate<String, Message> kafkaTemplate;
    KafkaProperties kafkaProperties;
    public boolean send(@NonNull Message message) {
        var future = kafkaTemplate.send(kafkaProperties.getMessageTopicSettings().getName(), message);
        try{
            var result = future.get();
            log.info("Successfully send to: {} with offset: {} with partition: {}, message: {}",
                    result.getRecordMetadata().topic(),
                    result.getRecordMetadata().offset(),
                    result.getRecordMetadata().partition(), message);
            return true;
        } catch (ExecutionException | InterruptedException e) {
            log.error("Can't send message: {}", message, e);
            return false;
        }
    }
}
