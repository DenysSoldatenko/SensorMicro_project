package com.example.sensorstorage.models;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.ToString;

/**
 * Represents a summary of sensor data aggregated by measurement type.
 */
@Getter
@ToString(onlyExplicitlyIncluded = true)
public class SummaryData {

  @ToString.Include
  private final long sensorId;

  // Map from measurement type to summary entries (e.g., AVG, MIN, MAX, etc.)
  private final Map<MeasurementType, List<SummaryEntry>> values = new EnumMap<>(MeasurementType.class);

  public SummaryData(long sensorId) {
    this.sensorId = sensorId;
  }

  /**
   * Adds a summary entry to the specified measurement type.
   *
   * @param type  the measurement type (e.g., TEMPERATURE)
   * @param entry the summary entry (e.g., AVG, MAX, etc.)
   */
  public void addValue(MeasurementType type, SummaryEntry entry) {
    values.computeIfAbsent(type, k -> new ArrayList<>()).add(entry);
  }

  /**
   * Immutable representation of a summary entry for a sensor measurement.
   */
  public record SummaryEntry(SummaryType type, double value, long counter) {}
}
