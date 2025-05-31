package com.example.sensoranalyzer.services;

import com.example.sensoranalyzer.models.SensorData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Service that listens for SensorData messages from Kafka topics.
 */
@Slf4j
@Service
public class SensorDataConsumerService {

  @KafkaListener(
    topics = "#{kafkaTopicProperties.topics.temperature}",
    groupId = "sensor-data-group",
    containerFactory = "kafkaListenerContainerFactory"
  )
  public void consumeTemperature(SensorData data) {
    log.info("Received TEMPERATURE data: {}", data);
  }

  @KafkaListener(
    topics = "#{kafkaTopicProperties.topics.voltage}",
    groupId = "sensor-data-group",
    containerFactory = "kafkaListenerContainerFactory"
  )
  public void consumeVoltage(SensorData data) {
    log.info("Received VOLTAGE data: {}", data);
  }

  @KafkaListener(
    topics = "#{kafkaTopicProperties.topics.power}",
    groupId = "sensor-data-group",
    containerFactory = "kafkaListenerContainerFactory"
  )
  public void consumePower(SensorData data) {
    log.info("Received POWER data: {}", data);
  }
}
