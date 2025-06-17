package com.example.sensoranalyzer.services.impl;

import com.example.sensoranalyzer.models.MeasurementType;
import com.example.sensoranalyzer.models.SensorData;
import com.example.sensoranalyzer.models.SummaryData;
import com.example.sensoranalyzer.models.SummaryType;
import com.example.sensoranalyzer.repositories.SummaryRepository;
import com.example.sensoranalyzer.services.SummaryService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service implementation for handling summary data aggregation and retrieval for sensors.
 * Processes incoming sensor data and provides aggregated summaries based on measurement and summary types.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService {

  private final SummaryRepository summaryRepository;

  @Override
  public SummaryData getSummaryForSensor(Long sensorId, Set<MeasurementType> measurementTypes, Set<SummaryType> summaryTypes) {
    if (sensorId == null || sensorId <= 0) {
      throw new IllegalArgumentException("Sensor Id must be a positive number!");
    }

    Set<MeasurementType> effectiveMeasurementTypes = (measurementTypes == null || measurementTypes.isEmpty())
        ? Set.of(MeasurementType.values())
        : measurementTypes;

    Set<SummaryType> effectiveSummaryTypes = (summaryTypes == null || summaryTypes.isEmpty())
        ? Set.of(SummaryType.values())
        : summaryTypes;

    log.info(
        "Fetching summary for sensorId={}, measurementTypes={}, summaryTypes={}",
        sensorId, effectiveMeasurementTypes, effectiveSummaryTypes
    );

    return summaryRepository.findBySensorId(sensorId, effectiveMeasurementTypes, effectiveSummaryTypes)
        .orElseThrow(() -> new RuntimeException("Sensor summary not found for ID: " + sensorId));
  }

  @Override
  public void processSensorData(SensorData sensorData) {
    if (sensorData == null) {
      throw new IllegalArgumentException("sensorData cannot be null");
    }
    log.info("Processing sensor data: {}", sensorData);
    summaryRepository.updateSummaryWithSensorData(sensorData);
  }
}
