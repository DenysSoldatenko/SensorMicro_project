package com.example.sensoranalyzer.services.impl;

import com.example.sensoranalyzer.models.SensorData;
import com.example.sensoranalyzer.services.CdcEventConsumerService;
import com.example.sensoranalyzer.services.SummaryService;
import com.example.sensoranalyzer.utils.DebeziumPayloadMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DebeziumEventConsumerImpl implements CdcEventConsumerService<ConsumerRecord<String, String>> {

  private final SummaryService summaryService;
  private final DebeziumPayloadMapper payloadMapper;

  @Override
  public void handle(ConsumerRecord<String, String> record) {
    SensorData data = payloadMapper.map(record.value());
    summaryService.processSensorData(data);
  }
}
