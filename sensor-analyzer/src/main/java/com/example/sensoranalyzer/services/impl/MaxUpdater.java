package com.example.sensoranalyzer.services.impl;

import static com.example.sensoranalyzer.models.SummaryType.MAX;
import static java.lang.Double.parseDouble;

import com.example.sensoranalyzer.configurations.RedisSchema;
import com.example.sensoranalyzer.models.SensorData;
import com.example.sensoranalyzer.services.SummaryUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * {@link SummaryUpdater} implementation that updates the maximum measurement value for a sensor in Redis.
 */
@Component
@RequiredArgsConstructor
public class MaxUpdater implements SummaryUpdater {

  private final RedisTemplate<String, String> redisTemplate;

  @Override
  public void update(SensorData data) {
    HashOperations<String, String, String> ops = redisTemplate.opsForHash();
    String key = RedisSchema.summaryKey(data.getSensorId(), data.getMeasurementType());

    String current = ops.get(key, MAX.name().toLowerCase());
    if (current == null || data.getMeasurement() > parseDouble(current)) {
      ops.put(key, MAX.name().toLowerCase(), String.valueOf(data.getMeasurement()));
    }
  }
}
