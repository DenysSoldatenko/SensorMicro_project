package com.example.sensoranalyzer.configurations;

import com.example.sensoranalyzer.models.SensorData;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConfiguration {

  private final ConsumerFactory<String, SensorData> consumerFactory;

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, SensorData> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, SensorData> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory);
    factory.setConcurrency(3);
    return factory;
  }
}
