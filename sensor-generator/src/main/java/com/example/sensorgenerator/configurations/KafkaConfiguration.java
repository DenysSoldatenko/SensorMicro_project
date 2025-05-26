package com.example.sensorgenerator.configurations;

import com.example.sensorgenerator.models.SensorData;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@RequiredArgsConstructor
public class KafkaConfiguration {

  private final ProducerFactory<String, SensorData> producerFactory;

  @Bean
  public KafkaTemplate<String, SensorData> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory);
  }

  @Bean
  public NewTopic temperatureTopic(KafkaTopicProperties props) {
    return TopicBuilder.name(props.getTopics().getTemperature())
      .partitions(3)
      .replicas(1)
      .build();
  }

  @Bean
  public NewTopic voltageTopic(KafkaTopicProperties props) {
    return TopicBuilder.name(props.getTopics().getVoltage())
      .partitions(3)
      .replicas(1)
      .build();
  }

  @Bean
  public NewTopic powerTopic(KafkaTopicProperties props) {
    return TopicBuilder.name(props.getTopics().getPower())
      .partitions(3)
      .replicas(1)
      .build();
  }
}
