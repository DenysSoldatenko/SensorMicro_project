package com.example.sensoranalyzer.services;

import com.example.sensoranalyzer.models.SensorData;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Service that listens for SensorData messages from Kafka topics.
 */
@Slf4j
@Service
public class SensorDataConsumerService {

  private final static int MAX_MESSAGES = 5;
  private final Deque<SensorData> temperatureMessages = new LinkedList<>();
  private final Deque<SensorData> voltageMessages = new LinkedList<>();
  private final Deque<SensorData> powerMessages = new LinkedList<>();

  @KafkaListener(
    topics = "#{kafkaTopicProperties.topics.temperature}",
    groupId = "sensor-data-group",
    containerFactory = "kafkaListenerContainerFactory"
  )
  public void consumeTemperature(SensorData data) {
    log.info("Received TEMPERATURE data: {}", data);
    addMessage(temperatureMessages, data);
  }

  @KafkaListener(
    topics = "#{kafkaTopicProperties.topics.voltage}",
    groupId = "sensor-data-group",
    containerFactory = "kafkaListenerContainerFactory"
  )
  public void consumeVoltage(SensorData data) {
    log.info("Received VOLTAGE data: {}", data);
    addMessage(voltageMessages, data);
  }

  @KafkaListener(
    topics = "#{kafkaTopicProperties.topics.power}",
    groupId = "sensor-data-group",
    containerFactory = "kafkaListenerContainerFactory"
  )
  public void consumePower(SensorData data) {
    log.info("Received POWER data: {}", data);
    addMessage(powerMessages, data);
  }

  private void addMessage(Deque<SensorData> deque, SensorData data) {
    if (deque.size() >= MAX_MESSAGES) {
      deque.removeFirst();
    }
    deque.addLast(data);
  }

  public List<SensorData> getLastTemperatureMessages() {
    return new ArrayList<>(temperatureMessages);
  }

  public List<SensorData> getLastVoltageMessages() {
    return new ArrayList<>(voltageMessages);
  }

  public List<SensorData> getLastPowerMessages() {
    return new ArrayList<>(powerMessages);
  }

  public List<SensorData> getLastMessages() {
    List<SensorData> all = new ArrayList<>();
    all.addAll(temperatureMessages);
    all.addAll(voltageMessages);
    all.addAll(powerMessages);
    return all;
  }
}
