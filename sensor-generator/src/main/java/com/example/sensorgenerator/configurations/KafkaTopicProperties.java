package com.example.sensorgenerator.configurations;

import com.example.sensorgenerator.models.MeasurementType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "sensor.kafka")
public class KafkaTopicProperties {

  private Topics topics = new Topics();

  public String getTopicForType(MeasurementType type) {
    return switch (type) {
      case TEMPERATURE -> topics.temperature;
      case VOLTAGE -> topics.voltage;
      case POWER -> topics.power;
    };
  }

  @Data
  public static class Topics {
    private String temperature;
    private String voltage;
    private String power;
  }
}
