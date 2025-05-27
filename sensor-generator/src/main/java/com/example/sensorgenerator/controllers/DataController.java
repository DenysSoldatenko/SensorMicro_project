package com.example.sensorgenerator.controllers;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.ResponseEntity.status;

import com.example.sensorgenerator.dtos.SensorDataDto;
import com.example.sensorgenerator.mappers.DataMapper;
import com.example.sensorgenerator.models.SensorData;
import com.example.sensorgenerator.services.KafkaDataService;
import com.example.sensorgenerator.utils.SensorDataFactory;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  public ResponseEntity<Void> sendSensorData(@Valid @RequestBody SensorDataDto requestDto) {
    SensorData sensorData = dataMapper.toEntity(requestDto);
    kafkaDataService.send(sensorData);
    log.info("Sensor data queued for sending: sensorId={}, type={}, timestamp={}", sensorData.getSensorId(), sensorData.getMeasurementType(), sensorData.getTimestamp());
    return status(ACCEPTED).build();
  }

  @PostMapping("/generate")
  public ResponseEntity<Void> generateAndSendBatch(
      @RequestParam(defaultValue = "10") @Min(1) @Max(1000) int count,
      @RequestParam(defaultValue = "0") @Min(0) @Max(60) int delaySeconds
  ) {
    log.info("Generating {} sensor data record(s) with delay of {} second(s) between sends", count, delaySeconds);
    List<SensorData> batch = SensorDataFactory.generateRandomBatch(count);
    kafkaDataService.sendBatchAsync(batch, delaySeconds);
    return status(ACCEPTED).build();
  }
}
