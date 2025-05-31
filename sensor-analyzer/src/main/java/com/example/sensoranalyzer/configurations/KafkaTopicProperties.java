package com.example.sensoranalyzer.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class that binds Kafka topic names for different measurement types
 * from the application's configuration file (e.g. {@code application.yaml}).
 *
 * <p>Example configuration:
 * <pre>
 * sensor.kafka.topics.temperature=sensor-temperature-topic
 * sensor.kafka.topics.voltage=sensor-voltage-topic
 * sensor.kafka.topics.power=sensor-power-topic
 * </pre>
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "sensor.kafka")
public class KafkaTopicProperties {

  private Topics topics = new Topics();

  /**
   * Inner class representing Kafka topic names for specific measurement types.
   * These values are expected to be set via application configuration.
   */
  @Data
  public static class Topics {
    private String temperature;
    private String voltage;
    private String power;
  }
}
