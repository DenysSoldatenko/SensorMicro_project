package com.example.sensoranalyzer.services;

import com.example.sensoranalyzer.models.SensorData;

public interface SummaryUpdater {
  void update(SensorData data);
}
