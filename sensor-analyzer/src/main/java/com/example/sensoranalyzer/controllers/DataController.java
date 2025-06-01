package com.example.sensoranalyzer.controllers;

import static org.springframework.http.ResponseEntity.ok;

import com.example.sensoranalyzer.models.SensorData;
import com.example.sensoranalyzer.services.SensorDataConsumerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller providing endpoints to retrieve recently consumed Kafka messages
 * of different measurement types (temperature, voltage, power).
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/data")
@Tag(name = "Sensor Data Controller", description = "Endpoints to inspect consumed Kafka messages")
public class DataController {

  private final SensorDataConsumerService consumerService;

  @Operation(
      summary = "Get recently consumed messages (all types)",
      description = "Returns the last 50 consumed Kafka messages for debugging/testing purposes"
  )
  @GetMapping("/messages")
  public ResponseEntity<List<SensorData>> getConsumedMessages() {
    return ok(consumerService.getLastMessages());
  }

  @Operation(
      summary = "Get recently consumed temperature messages",
      description = "Returns the last 50 consumed Kafka messages of type TEMPERATURE"
  )
  @GetMapping("/messages/temperature")
  public ResponseEntity<List<SensorData>> getTemperatureMessages() {
    return ok(consumerService.getLastTemperatureMessages());
  }

  @Operation(
      summary = "Get recently consumed voltage messages",
      description = "Returns the last 50 consumed Kafka messages of type VOLTAGE"
  )
  @GetMapping("/messages/voltage")
  public ResponseEntity<List<SensorData>> getVoltageMessages() {
    return ok(consumerService.getLastVoltageMessages());
  }

  @Operation(
      summary = "Get recently consumed power messages",
      description = "Returns the last 50 consumed Kafka messages of type POWER"
  )
  @GetMapping("/messages/power")
  public ResponseEntity<List<SensorData>> getPowerMessages() {
    return ok(consumerService.getLastPowerMessages());
  }
}
