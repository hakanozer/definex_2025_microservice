package com.works.note.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic newsTopic() {
        return new NewTopic("news-topic", 1, (short)1);
    }
}
