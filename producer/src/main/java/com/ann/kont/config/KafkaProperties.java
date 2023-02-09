package com.ann.kont.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Validated
@ConfigurationProperties(prefix = "app.kafka")
public class KafkaProperties {

    @NotNull
    private TopicSettings messageTopicSettings;

    @Data
    @Validated
    public static final class TopicSettings {
        @NotNull
        private String name;
        @Min(1)
        @Max(100)
        private int replicas=1;
        @Min(1)
        @Max(100)
        private int partitions = 10;
    }
}
