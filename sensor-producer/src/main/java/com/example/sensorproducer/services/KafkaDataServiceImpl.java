package com.example.sensorproducer.services;

import static java.util.Objects.requireNonNull;

import com.example.sensorproducer.configurations.KafkaTopicProperties;
import com.example.sensorproducer.models.SensorData;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * KafkaDataService implementation responsible for publishing sensor data to appropriate Kafka topics.
 */
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

  @Async
  @Override
  public void sendBatchAsync(List<SensorData> batch, int delaySeconds) {
    for (SensorData data : batch) {
      send(data);
      log.debug("Dispatched sensor data: sensorId={}, type={}, value={}, timestamp={}", data.getSensorId(), data.getMeasurementType(), data.getMeasurement(), data.getTimestamp());

      if (delaySeconds > 0) {
        try {
          Thread.sleep(delaySeconds * 1000L);
        } catch (InterruptedException ex) {
          Thread.currentThread().interrupt();
          log.warn("Batch transmission interrupted: {}", ex.getMessage());
          return;
        }
      }
    }
    log.info("Completed sending {} sensor data record(s)", batch.size());
  }
}
