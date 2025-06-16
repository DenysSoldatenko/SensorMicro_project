package com.example.sensoranalyzer.configurations;

import static java.util.Objects.requireNonNull;

import com.example.sensoranalyzer.models.MeasurementType;
import java.util.Locale;
import lombok.experimental.UtilityClass;

/**
 * Redis key and field schema for sensor summary storage.
 *
 * <p>Structure:
 * - Set:  sensor:sensors                        → members: sensorId (String)
 * - Hash: sensor:summary:{sensorId}:{mType}     → fields: min, max, sum, avg, counter
 *
 * <p>Examples:
 * - sensor:sensors
 * - sensor:summary:42:temperature
 */
@UtilityClass
public final class RedisSchema {

  public static final String NAMESPACE = "sensor";
  public static final String SENSORS_SET = NAMESPACE + ":sensors";
  private static final String SUMMARY_KEY_FMT = NAMESPACE + ":summary:%d:%s";

  /** Returns the Redis key for the set of all sensor IDs. */
  public static String sensorKeys() {
    return SENSORS_SET;
  }

  /**
   * Builds the Redis hash key for a specific sensor & measurement type.
   * Example: sensor:summary:42:temperature
   */
  public static String summaryKey(long sensorId, MeasurementType type) {
    if (sensorId <= 0) {
      throw new IllegalArgumentException("Sensor Id must be positive!");
    }
    requireNonNull(type, "MeasurementType must not be null!");
    return String.format(SUMMARY_KEY_FMT, sensorId, toField(type));
  }

  /** Normalized (lowercase) token for a MeasurementType used in keys. */
  public static String toField(MeasurementType type) {
    requireNonNull(type, "MeasurementType must not be null!");
    return type.name().toLowerCase(Locale.ROOT);
  }
}
