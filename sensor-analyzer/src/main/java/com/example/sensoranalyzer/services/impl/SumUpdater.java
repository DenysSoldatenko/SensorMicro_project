package com.example.sensoranalyzer.services.impl;

import static com.example.sensoranalyzer.models.SummaryType.SUM;

import com.example.sensoranalyzer.configurations.RedisSchema;
import com.example.sensoranalyzer.models.SensorData;
import com.example.sensoranalyzer.services.SummaryUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SumUpdater implements SummaryUpdater {

  private final RedisTemplate<String, String> redisTemplate;

  @Override
  public void update(SensorData data) {
    HashOperations<String, String, String> ops = redisTemplate.opsForHash();
    String key = RedisSchema.summaryKey(data.getSensorId(), data.getMeasurementType());
    ops.increment(key, SUM.name().toLowerCase(), data.getMeasurement());
  }
}
