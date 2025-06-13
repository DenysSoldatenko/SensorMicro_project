package com.example.sensorstorage.services;

import com.example.sensorstorage.models.MeasurementType;
import com.example.sensorstorage.models.SensorData;
import com.example.sensorstorage.models.SummaryData;
import com.example.sensorstorage.models.SummaryType;
import java.util.Set;

/**
 * Service for aggregating and retrieving sensor data summaries.
 */
public interface SummaryService {

  /**
   * Retrieves aggregated summary data for a specific sensor.
   *
   * @param sensorId         the ID of the sensor
   * @param measurementTypes optional filter for measurement types (e.g., TEMPERATURE)
   * @param summaryTypes     optional filter for summary statistics (e.g., AVG, MIN)
   * @return summary for the sensor
   */
  SummaryData getSummaryForSensor(Long sensorId, Set<MeasurementType> measurementTypes, Set<SummaryType> summaryTypes);

  /**
   * Processes incoming sensor data and updates internal summary state.
   *
   * @param sensorData the raw data from a sensor
   */
  void processSensorData(SensorData sensorData);
}
