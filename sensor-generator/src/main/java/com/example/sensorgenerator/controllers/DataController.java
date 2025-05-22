package com.example.sensorgenerator.controllers;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.ResponseEntity.status;

import com.example.sensorgenerator.dtos.SensorReadingRequestDto;
import com.example.sensorgenerator.models.SensorReading;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/data")
public class DataController {

  private final DataMapper dataMapper;
  private final KafkaDataService kafkaDataService;

  @PostMapping("/send")
  public ResponseEntity<Void> sendSensorData(@Valid @RequestBody SensorReadingRequestDto requestDto) {
    log.info("Received sensor data: {}", requestDto);

    SensorReading sensorReading = dataMapper.toEntity(requestDto);
    kafkaDataService.send(sensorReading);

    log.info("Sensor data queued for Kafka sending.");
    return status(ACCEPTED).build();
  }
}
