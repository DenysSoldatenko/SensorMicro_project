package com.example.sensoranalyzer.utils;

import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;

import com.example.sensoranalyzer.configurations.RedisSchema;
import com.example.sensoranalyzer.models.MeasurementType;
import com.example.sensoranalyzer.models.SummaryData;
import com.example.sensoranalyzer.models.SummaryData.SummaryEntry;
import com.example.sensoranalyzer.models.SummaryType;
import java.util.Set;
import lombok.experimental.UtilityClass;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Utility for reconstructing {@link SummaryData} objects from Redis-stored hash values.
 */
@UtilityClass
public final class SummaryBuilder {

  /**
   * Builds a {@link SummaryData} object for a sensor from Redis.
   *
   * <p>Iterates over the provided measurement types and summary types, reads the values
   * from Redis, and adds them to the summary object.
   *
   * @param redisTemplate  Redis template for accessing hash values
   * @param sensorId       the ID of the sensor
   * @param measurementTypes the measurement types to include
   * @param summaryTypes   the summary types to include (MIN, MAX, SUM, AVG, etc.)
   * @return a populated {@link SummaryData} object
   */
  public static SummaryData buildSummary(RedisTemplate<String, String> redisTemplate, long sensorId, Set<MeasurementType> measurementTypes, Set<SummaryType> summaryTypes) {
    HashOperations<String, String, String> ops = redisTemplate.opsForHash();
    SummaryData summary = new SummaryData(sensorId);

    for (MeasurementType measurementType : measurementTypes) {
      String redisKey = RedisSchema.summaryKey(sensorId, measurementType);

      for (SummaryType summaryType : summaryTypes) {
        String rawValue = ops.get(redisKey, summaryType.name().toLowerCase());
        String counter = ops.get(redisKey, "counter");
        if (rawValue != null && counter != null) {
          SummaryEntry entry = new SummaryEntry(summaryType, parseDouble(rawValue), parseLong(counter));
          summary.addValue(measurementType, entry);
        }
      }
    }
    return summary;
  }
}
