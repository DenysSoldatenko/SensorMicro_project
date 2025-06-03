package com.example.sensoranalyzer.services;

import static com.example.sensoranalyzer.models.MeasurementType.POWER;
import static com.example.sensoranalyzer.models.MeasurementType.TEMPERATURE;
import static com.example.sensoranalyzer.models.MeasurementType.VOLTAGE;

import com.example.sensoranalyzer.models.SensorData;
import com.example.sensoranalyzer.utils.SensorDataStore;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link KafkaDataService} that provides access to
 * recently consumed sensor data messages for different measurement types.
 *
 * <p>This service delegates storage and retrieval responsibilities
 * to a {@link SensorDataStore} which maintains bounded in-memory queues
 * of the latest consumed messages per measurement type.
 */
@Service
@RequiredArgsConstructor
public class KafkaDataServiceImpl implements KafkaDataService {

  private final SensorDataStore sensorDataStore;

  @Override
  public List<SensorData> getLastTemperatureMessages() {
    return sensorDataStore.getLastMessagesByType(TEMPERATURE);
  }

  @Override
  public List<SensorData> getLastVoltageMessages() {
    return sensorDataStore.getLastMessagesByType(VOLTAGE);
  }

  @Override
  public List<SensorData> getLastPowerMessages() {
    return sensorDataStore.getLastMessagesByType(POWER);
  }

  @Override
  public List<SensorData> getLastMessages() {
    return sensorDataStore.getAllLastMessages();
  }
}