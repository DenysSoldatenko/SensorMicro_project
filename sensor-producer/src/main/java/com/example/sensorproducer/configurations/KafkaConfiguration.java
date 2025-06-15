package com.example.sensorproducer.configurations;

import com.example.sensorproducer.models.SensorData;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

/**
 * Kafka configuration class responsible for defining Kafka-related beans,
 * including KafkaTemplate and topic declarations for sensor data.
 */
@Configuration
@RequiredArgsConstructor
public class KafkaConfiguration {

  private final ProducerFactory<String, SensorData> producerFactory;

  /**
   * Configures a KafkaTemplate for sending SensorData messages.
   *
   * @return KafkaTemplate used by services to send messages to Kafka topics.
   */
  @Bean
  public KafkaTemplate<String, SensorData> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory);
  }

  /**
   * Creates the Kafka topic for temperature sensor data if it does not exist.
   *
   * @param props the Kafka topic properties loaded from configuration.
   * @return NewTopic for temperature data.
   */
  @Bean
  public NewTopic temperatureTopic(KafkaTopicProperties props) {
    return TopicBuilder.name(props.getTopics().getTemperature())
      .partitions(3)
      .replicas(1)
      .build();
  }

  /**
   * Creates the Kafka topic for voltage sensor data if it does not exist.
   *
   * @param props the Kafka topic properties loaded from configuration.
   * @return NewTopic for voltage data.
   */
  @Bean
  public NewTopic voltageTopic(KafkaTopicProperties props) {
    return TopicBuilder.name(props.getTopics().getVoltage())
      .partitions(3)
      .replicas(1)
      .build();
  }

  /**
   * Creates the Kafka topic for power sensor data if it does not exist.
   *
   * @param props the Kafka topic properties loaded from configuration.
   * @return NewTopic for power data.
   */
  @Bean
  public NewTopic powerTopic(KafkaTopicProperties props) {
    return TopicBuilder.name(props.getTopics().getPower())
      .partitions(3)
      .replicas(1)
      .build();
  }
}
