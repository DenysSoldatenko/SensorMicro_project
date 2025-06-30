package com.example.sensoranalyzer.utils;

import com.example.sensoranalyzer.services.CdcEventConsumerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Kafka listener component that consumes Debezium CDC events from the {@code sensor_data} topic.
 *
 * <p>Delegates the processing of each Kafka record to a {@link CdcEventConsumerService} instance.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DebeziumKafkaListener {

  private final CdcEventConsumerService<ConsumerRecord<String, String>> eventConsumer;

  /**
   * Handles incoming Kafka messages from the {@code sensor_data} topic.
   *
   * <p>The method delegates to {@link CdcEventConsumerService#handle(Object)}.
   * If processing fails, the error is logged with detailed Kafka record information
   * and the exception is rethrown.
   *
   * @param record the Kafka {@link ConsumerRecord} containing the CDC event; must not be {@code null}
   * @throws RuntimeException if event processing fails
   */
  @KafkaListener(topics = "sensor_data")
  public void onMessage(ConsumerRecord<String, String> record) {
    try {
      eventConsumer.handle(record);
    } catch (Exception ex) {
      log.error("Failed to process CDC event. topic={}, partition={}, offset={}", record.topic(), record.partition(), record.offset(), ex);
      throw ex;
    }
  }
}
