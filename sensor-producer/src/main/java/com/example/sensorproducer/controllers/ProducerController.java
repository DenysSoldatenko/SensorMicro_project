package com.example.sensorproducer.controllers;

import com.example.sensorproducer.dtos.SensorDataDto;
import com.example.sensorproducer.mappers.DataMapper;
import com.example.sensorproducer.models.SensorData;
import com.example.sensorproducer.services.KafkaDataService;
import com.example.sensorproducer.utils.SensorDataFactory;
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
public class ProducerController {

  private static final int DEFAULT_BATCH_SIZE = 10;
  private static final int MAX_BATCH_SIZE = 1000;
  private static final int DEFAULT_DELAY_SECONDS = 0;
  private static final int MAX_DELAY_SECONDS = 60;

  private final DataMapper dataMapper;
  private final KafkaDataService kafkaDataService;

  /**
   * Accepts a sensor data payload and sends it to Kafka.
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
    log.info("Sensor data sent to Kafka | sensorId={} | type={} | timestamp={}", sensorData.getSensorId(), sensorData.getMeasurementType(), sensorData.getTimestamp());
    return ResponseEntity.accepted().build();
  }

  /**
   * Generates random sensor data and sends each record with an optional delay.
   *
   * @param count        number of records to generate (1–1000)
   * @param delaySeconds delay between sends (0–60 seconds)
   * @return HTTP 202 Accepted
   */
  @Operation(
      summary = "Generate and send a batch of sensor data records",
      description = "Auto-generates a batch of sensor data records and queues them for Kafka sending. Optional delay in seconds between each send"
  )
  @PostMapping("/generate")
  public ResponseEntity<Void> generateAndSendBatch(
      @RequestParam(name = "count", defaultValue = "10") @Min(DEFAULT_BATCH_SIZE) @Max(MAX_BATCH_SIZE) int count,
      @RequestParam(name = "delaySeconds", defaultValue = "0") @Min(DEFAULT_DELAY_SECONDS) @Max(MAX_DELAY_SECONDS) int delaySeconds
  ) {
    log.info("Starting batch sensor data generation | count={} | delayBetweenSends={}s", count, delaySeconds);
    List<SensorData> sensorDataBatch = SensorDataFactory.generateRandomBatch(count);
    kafkaDataService.sendBatchAsync(sensorDataBatch, delaySeconds);
    log.info("Dispatched sensor data batch to Kafka | batchSize={} | delay={}s", count, delaySeconds);
    return ResponseEntity.accepted().build();
  }
}
