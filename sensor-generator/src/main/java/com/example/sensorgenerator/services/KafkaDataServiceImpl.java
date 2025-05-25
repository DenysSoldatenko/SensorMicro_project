package com.example.sensorgenerator.services;

import static java.util.Objects.requireNonNull;

import com.example.sensorgenerator.configurations.KafkaTopicProperties;
import com.example.sensorgenerator.models.SensorData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaDataServiceImpl implements KafkaDataService {

  private final KafkaTopicProperties topics;
  private final KafkaTemplate<String, SensorData> kafka;

  @Override
  public void send(SensorData data) {
    requireNonNull(data, "SensorData is null");
    requireNonNull(data.getMeasurementType(), "MeasurementType is null");

    String topic = topics.getTopicForType(data.getMeasurementType());
    if (topic == null || topic.isBlank()) {
      throw new IllegalStateException("No Kafka topic for type: " + data.getMeasurementType());
    }

    kafka.send(topic, data).whenComplete((res, ex) -> {
      if (ex == null) {
        var meta = res.getRecordMetadata();
        log.info("Sent to topic={} partition={} offset={}", meta.topic(), meta.partition(), meta.offset());
      } else {
        log.error("Failed to send to topic={}: {}", topic, ex.getMessage(), ex);
      }
    });
  }
}
