package com.example.sensorconsumer.services;

import static com.example.sensorconsumer.models.MeasurementType.POWER;
import static com.example.sensorconsumer.models.MeasurementType.TEMPERATURE;
import static com.example.sensorconsumer.models.MeasurementType.VOLTAGE;

import com.example.sensorconsumer.models.SensorData;
import com.example.sensorconsumer.repositories.SensorDataRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link KafkaDataService} that provides access to
 * recently consumed sensor data messages for different measurement types.
 */
@Service
@RequiredArgsConstructor
public class KafkaDataServiceImpl implements KafkaDataService {

  private final SensorDataRepository sensorDataRepository;

  @Override
  public List<SensorData> getLastTemperatureMessages(int limit) {
    return sensorDataRepository.findRecentByTypeLimited(TEMPERATURE.name(), limit);
  }

  @Override
  public List<SensorData> getLastVoltageMessages(int limit) {
    return sensorDataRepository.findRecentByTypeLimited(VOLTAGE.name(), limit);
  }

  @Override
  public List<SensorData> getLastPowerMessages(int limit) {
    return sensorDataRepository.findRecentByTypeLimited(POWER.name(), limit);
  }

  @Override
  public List<SensorData> getLastMessages(int limit) {
    return sensorDataRepository.findRecentAllLimited(limit);
  }
}