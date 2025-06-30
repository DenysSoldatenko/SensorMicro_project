package com.example.sensoranalyzer.services;

import com.example.sensoranalyzer.models.SensorData;

/**
 * Service interface for updating summary information based on sensor data.
 */
public interface SummaryUpdater {

  /**
   * Updates the summary information using the provided sensor data.
   *
   * @param data the {@link SensorData} instance containing the latest measurement;
   *             must not be {@code null}
   */
  void update(SensorData data);
}
