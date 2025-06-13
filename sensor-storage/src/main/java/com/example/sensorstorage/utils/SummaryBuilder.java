package com.example.sensorstorage.utils;

import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;

import com.example.sensorstorage.configurations.RedisSchema;
import com.example.sensorstorage.models.MeasurementType;
import com.example.sensorstorage.models.SummaryData;
import com.example.sensorstorage.models.SummaryData.SummaryEntry;
import com.example.sensorstorage.models.SummaryType;
import java.util.Set;
import lombok.experimental.UtilityClass;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Utility for reconstructing {@link SummaryData} objects from Redis-stored hash values.
 */
@UtilityClass
public final class SummaryBuilder {

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
