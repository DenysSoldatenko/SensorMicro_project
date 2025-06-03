package com.example.sensoranalyzer.utils;

import static com.example.sensoranalyzer.models.MeasurementType.POWER;
import static com.example.sensoranalyzer.models.MeasurementType.TEMPERATURE;
import static com.example.sensoranalyzer.models.MeasurementType.VOLTAGE;

import com.example.sensoranalyzer.models.SensorData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Kafka consumer service responsible for listening to sensor data on specified topics
 * and delegating storage to {@link SensorDataStore}.
 *
 * <p>This service consumes sensor data messages from Kafka topics for different measurement types
 * (e.g., temperature, voltage, power) and stores them in a thread-safe manner using the injected
 * {@link SensorDataStore}. It ensures input validation and proper logging for observability.
 * The design is minimal, adhering to the Single Responsibility Principle, and is extensible for
 * additional measurement types.</p>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SensorDataConsumer {

  private final SensorDataStore sensorDataStore;

  /**
   * Consumes temperature sensor data from the configured Kafka topic.
   *
   * <p>Logs the received data and delegates storage to the {@link SensorDataStore}.
   * Validates the input to ensure it is not null.</p>
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
    sensorDataStore.addMessage(TEMPERATURE, data);
  }

  /**
   * Consumes voltage sensor data from the configured Kafka topic.
   *
   * <p>Logs the received data and delegates storage to the {@link SensorDataStore}.
   * Validates the input to ensure it is not null.</p>
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
    sensorDataStore.addMessage(VOLTAGE, data);
  }

  /**
   * Consumes power sensor data from the configured Kafka topic.
   *
   * <p>Logs the received data and delegates storage to the {@link SensorDataStore}.
   * Validates the input to ensure it is not null.</p>
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
    sensorDataStore.addMessage(POWER, data);
  }
}
