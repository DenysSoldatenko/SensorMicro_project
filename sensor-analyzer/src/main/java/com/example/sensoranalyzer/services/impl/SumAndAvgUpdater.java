package com.example.sensoranalyzer.services.impl;

import static com.example.sensoranalyzer.models.SummaryType.AVG;
import static com.example.sensoranalyzer.models.SummaryType.SUM;
import static java.lang.String.valueOf;

import com.example.sensoranalyzer.configurations.RedisSchema;
import com.example.sensoranalyzer.models.SensorData;
import com.example.sensoranalyzer.services.SummaryUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * {@link SummaryUpdater} implementation that updates the sum and average measurements for a sensor in Redis.
 */
@Component
@RequiredArgsConstructor
public class SumAndAvgUpdater implements SummaryUpdater {

  private static final String COUNTER = "counter";
  private final RedisTemplate<String, String> redisTemplate;

  @Override
  public void update(SensorData data) {
    HashOperations<String, String, String> ops = redisTemplate.opsForHash();
    String key = RedisSchema.summaryKey(data.getSensorId(), data.getMeasurementType());

    double sum = ops.increment(key, SUM.name().toLowerCase(), data.getMeasurement());
    long count = ops.increment(key, COUNTER, 1);
    ops.put(key, AVG.name().toLowerCase(), valueOf(sum / count));
  }
}
