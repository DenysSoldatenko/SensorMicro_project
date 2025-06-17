package com.example.sensorconsumer.services;

import com.example.sensorconsumer.models.SensorData;
import java.util.List;

/**
 * Service interface for accessing recently consumed sensor data from Kafka.
 * Provides methods to retrieve the latest messages filtered by measurement type.
 */
public interface KafkaDataService {

  /**
   * Retrieves the most recent temperature sensor messages consumed from Kafka.
   *
   * @param limit the maximum number of messages to retrieve
   * @return a list of recent temperature sensor data
   */
  List<SensorData> getLastTemperatureMessages(int limit);

  /**
   * Retrieves the most recent voltage sensor messages consumed from Kafka.
   *
   * @param limit the maximum number of messages to retrieve
   * @return a list of recent voltage sensor data
   */
  List<SensorData> getLastVoltageMessages(int limit);

  /**
   * Retrieves the most recent power sensor messages consumed from Kafka.
   *
   * @param limit the maximum number of messages to retrieve
   * @return a list of recent power sensor data
   */
  List<SensorData> getLastPowerMessages(int limit);

  /**
   * Retrieves the most recent sensor messages across all measurement types.
   *
   * @param limit the maximum number of messages to retrieve
   * @return a list of recent sensor data of any type
   */
  List<SensorData> getLastMessages(int limit);
}
