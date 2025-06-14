package com.example.sensorconsumer.services;

import com.example.sensorconsumer.models.SensorData;
import java.util.List;

public interface KafkaDataService {

  List<SensorData> getLastTemperatureMessages(int limit);

  List<SensorData> getLastVoltageMessages(int limit);

  List<SensorData> getLastPowerMessages(int limit);

  List<SensorData> getLastMessages(int limit);
}
