package com.example.sensorgenerator.services;

import com.example.sensorgenerator.models.SensorData;

public interface KafkaDataService {

  void send(SensorData sensorData);
}
