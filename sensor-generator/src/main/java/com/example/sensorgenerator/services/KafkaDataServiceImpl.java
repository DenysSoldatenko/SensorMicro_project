package com.example.sensorgenerator.services;

import com.example.sensorgenerator.models.MeasurementType;
import com.example.sensorgenerator.models.SensorData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaDataServiceImpl implements KafkaDataService {

  private final KafkaTemplate<String, SensorData> kafkaTemplate;

  @Override
  public void send(SensorData sensorData) {
    String topic = resolveTopic(sensorData.getMeasurementType());
    kafkaTemplate.send(topic, sensorData);
  }

  /**
   * Resolve Kafka topic name from measurement type.
   *
   * @param measurementType the measurement type
   * @return the Kafka topic name
   */
  private String resolveTopic(MeasurementType measurementType) {
    return switch (measurementType) {
      case TEMPERATURE -> "sensor-temperature-topic";
      case VOLTAGE -> "sensor-voltage-topic";
      case POWER -> "sensor-power-topic";
    };
  }
}
