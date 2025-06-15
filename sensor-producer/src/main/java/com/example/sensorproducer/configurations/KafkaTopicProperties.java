package com.example.sensorproducer.configurations;

import com.example.sensorproducer.models.MeasurementType;
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
   * Resolves the Kafka topic name for the given measurement type.
   *
   * @param type the measurement type (e.g., TEMPERATURE, VOLTAGE, POWER)
   * @return the Kafka topic name corresponding to the type
   */
  public String getTopicForType(MeasurementType type) {
    return switch (type) {
      case TEMPERATURE -> topics.temperature;
      case VOLTAGE -> topics.voltage;
      case POWER -> topics.power;
    };
  }

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
