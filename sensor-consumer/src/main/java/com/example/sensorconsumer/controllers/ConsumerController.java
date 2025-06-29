package com.example.sensorconsumer.controllers;

import static org.springframework.http.ResponseEntity.ok;

import com.example.sensorconsumer.models.SensorData;
import com.example.sensorconsumer.services.KafkaDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
public class ConsumerController {

  private final KafkaDataService consumerService;

  /**
   * Retrieves the most recent sensor messages for all measurement types.
   *
   * @param limit the number of messages to retrieve (1–20)
   * @return a list of recent sensor messages
   */
  @Operation(
      summary = "Get recent sensor messages",
      description = "Retrieve the most recent messages consumed from Kafka for all measurement types"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Ok: Successfully retrieved recent sensor messages"),
      @ApiResponse(responseCode = "400", description = "Bad Request: invalid limit parameter", content = @Content()),
      @ApiResponse(responseCode = "500", description = "Internal Server Error: failure retrieving messages from service", content = @Content())
  })
  @GetMapping("/messages")
  public ResponseEntity<List<SensorData>> getConsumedMessages(
      @RequestParam(defaultValue = "1") @Min(1) @Max(20) int limit
  ) {
    return ok(consumerService.getLastMessages(limit));
  }

  /**
   * Retrieves the most recent temperature sensor messages.
   *
   * @param limit the number of messages to retrieve (1–20)
   * @return a list of recent temperature messages
   */
  @Operation(
      summary = "Get recent temperature messages",
      description = "Retrieve the most recent temperature-related messages consumed from Kafka"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Ok: Successfully retrieved recent temperature messages"),
      @ApiResponse(responseCode = "400", description = "Bad Request: invalid limit parameter", content = @Content()),
      @ApiResponse(responseCode = "500", description = "Internal Server Error: failure retrieving temperature messages", content = @Content())
  })
  @GetMapping("/messages/temperature")
  public ResponseEntity<List<SensorData>> getTemperatureMessages(
      @RequestParam(defaultValue = "1") @Min(1) @Max(20) int limit
  ) {
    return ok(consumerService.getLastTemperatureMessages(limit));
  }

  /**
   * Retrieves the most recent voltage sensor messages.
   *
   * @param limit the number of messages to retrieve (1–20)
   * @return a list of recent voltage messages
   */
  @Operation(
      summary = "Get recent voltage messages",
      description = "Retrieve the most recent voltage-related messages consumed from Kafka"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Ok: Successfully retrieved recent voltage messages"),
      @ApiResponse(responseCode = "400", description = "Bad Request: invalid limit parameter", content = @Content()),
      @ApiResponse(responseCode = "500", description = "Internal Server Error: failure retrieving voltage messages", content = @Content())
  })
  @GetMapping("/messages/voltage")
  public ResponseEntity<List<SensorData>> getVoltageMessages(
      @RequestParam(defaultValue = "1") @Min(1) @Max(20) int limit
  ) {
    return ok(consumerService.getLastVoltageMessages(limit));
  }

  /**
   * Retrieves the most recent power sensor messages.
   *
   * @param limit the number of messages to retrieve (1–20)
   * @return a list of recent power messages
   */
  @Operation(
      summary = "Get recent power messages",
      description = "Retrieve the most recent power-related messages consumed from Kafka"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Ok: Successfully retrieved recent power messages"),
      @ApiResponse(responseCode = "400", description = "Bad Request: invalid limit parameter", content = @Content()),
      @ApiResponse(responseCode = "500", description = "Internal Server Error: failure retrieving power messages", content = @Content())
  })
  @GetMapping("/messages/power")
  public ResponseEntity<List<SensorData>> getPowerMessages(
      @RequestParam(defaultValue = "1") @Min(1) @Max(20) int limit
  ) {
    return ok(consumerService.getLastPowerMessages(limit));
  }
}
