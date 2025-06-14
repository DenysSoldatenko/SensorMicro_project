package com.example.sensorconsumer.configurations;

import com.example.sensorconsumer.models.SensorData;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

/**
 * Kafka configuration class for setting up the Kafka consumer infrastructure.
 */
@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConfiguration {

  private final ConsumerFactory<String, SensorData> consumerFactory;

  /**
   * Configures a {@link ConcurrentKafkaListenerContainerFactory} bean used by Kafka listeners.
   *
   * @return a configured {@link ConcurrentKafkaListenerContainerFactory} for SensorData messages
   */
  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, SensorData> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, SensorData> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory);
    factory.setConcurrency(3);
    return factory;
  }
}
