package com.bahadirmemis.mailsender.mailsender.kafka;

import com.bahadirmemis.mailsender.mailsender.mail.MailSendRequestDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@EnableKafka
@Configuration
public class KafkaConsumerConfiguration {

    @Value("${interprobe.kafka.address}")
    private String kafkaAddress;

    @Value("${interprobe.kafka.group-id}")
    private String groupId;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MailSendRequestDto> kafkaListenerContainerFactory(){

        ConcurrentKafkaListenerContainerFactory<String, MailSendRequestDto> factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory());

        return factory;
    }

    @Bean
    public ConsumerFactory<String, MailSendRequestDto> consumerFactory(){

        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, MailSendRequestDto.class);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config);
    }

}
