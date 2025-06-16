package com.example.sensoranalyzer.repositories;

import com.example.sensoranalyzer.models.MeasurementType;
import com.example.sensoranalyzer.models.SensorData;
import com.example.sensoranalyzer.models.SummaryData;
import com.example.sensoranalyzer.models.SummaryType;
import java.util.Optional;
import java.util.Set;

/**
 * Repository interface for querying and updating summary data.
 */
public interface SummaryRepository {

  /**
   * Retrieves a summary for a given sensor ID with optional filters.
   *
   * @param sensorId         the sensor identifier
   * @param measurementTypes filters on measurement types; if empty or null, all are considered
   * @param summaryTypes     filters on summary types; if empty or null, all are considered
   * @return an Optional containing the summary if found, otherwise empty
   */
  Optional<SummaryData> findBySensorId(long sensorId, Set<MeasurementType> measurementTypes, Set<SummaryType> summaryTypes);

  /**
   * Updates or inserts summary information based on incoming sensor data.
   *
   * @param sensorData the sensor data to be processed
   */
  void updateSummaryWithSensorData(SensorData sensorData);
}
