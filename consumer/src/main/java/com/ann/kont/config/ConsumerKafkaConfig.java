package com.ann.kont.config;

import com.ann.kont.model.Message;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(ConsumerKafkaProperties.class)
public class ConsumerKafkaConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String server;

    private final ConsumerKafkaProperties consumerKafkaProperties;

    @Bean
    public ConsumerFactory<String, Message> consumerFactory() {
        var config = new HashMap<String, Object>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, consumerKafkaProperties.getGroupId());
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, consumerKafkaProperties.getAutoOffsetReset());
        return new DefaultKafkaConsumerFactory<>(config);
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Message> kafkaListener(ConsumerFactory<String, Message> consumerFactory){
        var factory = new ConcurrentKafkaListenerContainerFactory<String, Message>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}
