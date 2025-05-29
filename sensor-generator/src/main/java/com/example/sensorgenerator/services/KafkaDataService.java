package com.example.sensorgenerator.services;

import com.example.sensorgenerator.models.SensorData;
import java.util.List;

/**
 * Service interface for sending sensor data to Kafka topics.
 *
 * <p>Supports both individual and batched asynchronous message dispatching.
 */
public interface KafkaDataService {

  /**
   * Sends a single {@link SensorData} record to the appropriate Kafka topic.
   *
   * @param sensorData the data to publish
   */
  void send(SensorData sensorData);

  /**
   * Sends a batch of sensor data records asynchronously, with an optional delay between each send.
   *
   * @param batch         the list of SensorData records to send
   * @param delaySeconds  delay (in seconds) between sending each record; 0 for no delay
   */
  void sendBatchAsync(List<SensorData> batch, int delaySeconds);
}
