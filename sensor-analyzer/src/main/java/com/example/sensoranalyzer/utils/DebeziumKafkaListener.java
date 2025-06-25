package com.example.sensoranalyzer.utils;

import com.example.sensoranalyzer.services.CdcEventConsumerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DebeziumKafkaListener {

  private final CdcEventConsumerService<ConsumerRecord<String, String>> eventConsumer;

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
