package com.example.sensorgenerator.controllers;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.ResponseEntity.status;

import com.example.sensorgenerator.dtos.SensorDataDto;
import com.example.sensorgenerator.mappers.DataMapper;
import com.example.sensorgenerator.models.SensorData;
import com.example.sensorgenerator.services.KafkaDataService;
import com.example.sensorgenerator.utils.SensorDataFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

/**
 * REST controller for managing sensor data operations including sending and generating sample data.
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/data")
@Tag(name = "Sensor Data Controller", description = "Endpoints for sending and generating sensor data")
public class DataController {

  private final DataMapper dataMapper;
  private final KafkaDataService kafkaDataService;

  /**
   * Accepts a sensor data payload and queues it for Kafka dispatch.
   *
   * @param requestDto the sensor data DTO
   * @return HTTP 202 Accepted
   */
  @Operation(
      summary = "Send sensor data",
      description = "Accepts a single sensor data payload and queues it for Kafka sending"
  )
  @PostMapping("/send")
  public ResponseEntity<Void> sendSensorData(@Valid @RequestBody SensorDataDto requestDto) {
    SensorData sensorData = dataMapper.toEntity(requestDto);
    kafkaDataService.send(sensorData);
    log.info("Sensor data queued for sending: sensorId={}, type={}, timestamp={}", sensorData.getSensorId(), sensorData.getMeasurementType(), sensorData.getTimestamp());
    return status(ACCEPTED).build();
  }

  /**
   * Generates a batch of random sensor data and sends them asynchronously to Kafka.
   * A delay between each send can be specified via request parameter.
   *
   * @param count         number of records to generate (default 10, max 1000)
   * @param delaySeconds  delay in seconds between each send (default 0, max 60)
   * @return HTTP 202 Accepted
   */
  @Operation(
      summary = "Generate and send a batch of sensor data records",
      description = "Auto-generates a batch of sensor data records and queues them for Kafka sending. Optional delay in seconds between each send"
  )
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
