package com.example.sensorconsumer.utils;

import com.example.sensorconsumer.models.SensorData;
import com.example.sensorconsumer.repositories.SensorDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Kafka consumer service responsible for listening to sensor data on specified topics
 * and delegating storage to {@link SensorDataRepository}.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SensorDataConsumer {

  private final SensorDataRepository sensorDataRepository;

  /**
   * Consumes temperature sensor data from the configured Kafka topic.
   *
   * @param data the sensor data received from the topic
   * @throws IllegalArgumentException if the data is null
   */
  @KafkaListener(
      topics = "#{kafkaTopicProperties.topics.temperature}",
      groupId = "sensor-data-group",
      containerFactory = "kafkaListenerContainerFactory"
  )
  public void consumeTemperature(SensorData data) {
    log.info("Received TEMPERATURE data: {}", data);
    sensorDataRepository.save(data);
  }

  /**
   * Consumes voltage sensor data from the configured Kafka topic.
   *
   * @param data the sensor data received from the topic
   * @throws IllegalArgumentException if the data is null
   */
  @KafkaListener(
      topics = "#{kafkaTopicProperties.topics.voltage}",
      groupId = "sensor-data-group",
      containerFactory = "kafkaListenerContainerFactory"
  )
  public void consumeVoltage(SensorData data) {
    log.info("Received VOLTAGE data: {}", data);
    sensorDataRepository.save(data);
  }

  /**
   * Consumes power sensor data from the configured Kafka topic.
   *
   * @param data the sensor data received from the topic
   * @throws IllegalArgumentException if the data is null
   */
  @KafkaListener(
      topics = "#{kafkaTopicProperties.topics.power}",
      groupId = "sensor-data-group",
      containerFactory = "kafkaListenerContainerFactory"
  )
  public void consumePower(SensorData data) {
    log.info("Received POWER data: {}", data);
    sensorDataRepository.save(data);
  }
}
