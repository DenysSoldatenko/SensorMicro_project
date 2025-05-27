package com.example.sensorgenerator.services;

import com.example.sensorgenerator.models.SensorData;
import java.util.List;

public interface KafkaDataService {

  void send(SensorData sensorData);

  void sendBatchAsync(List<SensorData> batch, int delaySeconds);
}
