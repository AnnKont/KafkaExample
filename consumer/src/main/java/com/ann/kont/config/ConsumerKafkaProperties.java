package com.ann.kont.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@Validated
@ConfigurationProperties(prefix = "spring.kafka.consumer")
public class ConsumerKafkaProperties {

    @NotBlank
    private String groupId;

    @NotBlank
    private String autoOffsetReset;
}
