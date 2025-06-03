package com.example.sensoranalyzer.services;

import com.example.sensoranalyzer.models.SensorData;
import java.util.List;

/**
 * Service interface for consuming and retrieving sensor data messages.
 *
 * <p>Provides access to the latest received messages of various measurement types
 * such as temperature, voltage, power, or all combined.
 */
public interface KafkaDataService {

  /**
   * Retrieves the most recently consumed temperature sensor messages.
   *
   * @return a list of {@link SensorData} objects representing temperature readings
   */
  List<SensorData> getLastTemperatureMessages();

  /**
   * Retrieves the most recently consumed voltage sensor messages.
   *
   * @return a list of {@link SensorData} objects representing voltage readings
   */
  List<SensorData> getLastVoltageMessages();

  /**
   * Retrieves the most recently consumed power sensor messages.
   *
   * @return a list of {@link SensorData} objects representing power readings
   */
  List<SensorData> getLastPowerMessages();

  /**
   * Retrieves the most recently consumed messages of all sensor types.
   *
   * @return a list of {@link SensorData} objects including all types of readings
   */
  List<SensorData> getLastMessages();
}
