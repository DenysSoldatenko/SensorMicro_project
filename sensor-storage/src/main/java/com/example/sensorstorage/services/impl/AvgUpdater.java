package com.example.sensorstorage.services.impl;

import static com.example.sensorstorage.models.SummaryType.AVG;
import static com.example.sensorstorage.models.SummaryType.SUM;
import static java.lang.Double.parseDouble;

import com.example.sensorstorage.configurations.RedisSchema;
import com.example.sensorstorage.models.SensorData;
import com.example.sensorstorage.services.SummaryUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AvgUpdater implements SummaryUpdater {

  private final RedisTemplate<String, String> redisTemplate;

  @Override
  public void update(SensorData data) {
    HashOperations<String, String, String> ops = redisTemplate.opsForHash();
    String key = RedisSchema.summaryKey(data.getSensorId(), data.getMeasurementType());

    Long counter = ops.increment(key, "counter", 1);
    String sumRaw = ops.get(key, SUM.name().toLowerCase());

    if (sumRaw != null) {
      double sum = parseDouble(sumRaw);
      double avg = sum / counter;
      ops.put(key, AVG.name().toLowerCase(), String.valueOf(avg));
    }
  }
}
