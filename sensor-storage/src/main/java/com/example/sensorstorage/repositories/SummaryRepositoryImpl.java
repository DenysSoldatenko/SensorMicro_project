package com.example.sensorstorage.repositories;

import com.example.sensorstorage.configurations.RedisSchema;
import com.example.sensorstorage.models.MeasurementType;
import com.example.sensorstorage.models.SensorData;
import com.example.sensorstorage.models.SummaryData;
import com.example.sensorstorage.models.SummaryType;
import com.example.sensorstorage.services.SummaryUpdater;
import com.example.sensorstorage.utils.SummaryBuilder;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SummaryRepositoryImpl implements SummaryRepository {

  private final List<SummaryUpdater> updaters;
  private final RedisTemplate<String, String> redisTemplate;

  @Override
  public Optional<SummaryData> findBySensorId(long sensorId, Set<MeasurementType> measurementTypes, Set<SummaryType> summaryTypes) {
    String sensorKey = RedisSchema.sensorKeys();
    Boolean exists = redisTemplate.opsForSet().isMember(sensorKey, String.valueOf(sensorId));

    return Boolean.FALSE.equals(exists)
      ? Optional.empty()
      : Optional.of(SummaryBuilder.buildSummary(redisTemplate, sensorId, measurementTypes, summaryTypes));
  }

  @Override
  public void updateSummaryWithSensorData(SensorData sensorData) {
    String sensorKey = RedisSchema.sensorKeys();
    redisTemplate.opsForSet().add(sensorKey, String.valueOf(sensorData.getSensorId()));

    // Apply all update strategies (OCP — new ones can be plugged in)
    updaters.forEach(updater -> updater.update(sensorData));
  }
}
