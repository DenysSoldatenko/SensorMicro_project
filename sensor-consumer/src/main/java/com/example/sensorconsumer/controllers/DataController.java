package com.example.sensorconsumer.controllers;

import static org.springframework.http.ResponseEntity.ok;

import com.example.sensorconsumer.models.SensorData;
import com.example.sensorconsumer.services.KafkaDataService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller providing endpoints to retrieve recently consumed Kafka messages
 * of different measurement types (temperature, voltage, power).
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/data")
@Tag(name = "Sensor Data Controller", description = "Endpoints to inspect consumed Kafka messages")
public class DataController {

  private final KafkaDataService consumerService;

  @GetMapping("/messages")
  public ResponseEntity<List<SensorData>> getConsumedMessages(
      @RequestParam(defaultValue = "1") @Min(1) @Max(20) int limit
  ) {
    return ok(consumerService.getLastMessages(limit));
  }

  @GetMapping("/messages/temperature")
  public ResponseEntity<List<SensorData>> getTemperatureMessages(
      @RequestParam(defaultValue = "1") @Min(1) @Max(20) int limit
  ) {
    return ok(consumerService.getLastTemperatureMessages(limit));
  }


  @GetMapping("/messages/voltage")
  public ResponseEntity<List<SensorData>> getVoltageMessages(
      @RequestParam(defaultValue = "1") @Min(1) @Max(20) int limit
  ) {
    return ok(consumerService.getLastVoltageMessages(limit));
  }

  @GetMapping("/messages/power")
  public ResponseEntity<List<SensorData>> getPowerMessages(
      @RequestParam(defaultValue = "1") @Min(1) @Max(20) int limit
  ) {
    return ok(consumerService.getLastPowerMessages(limit));
  }
}
