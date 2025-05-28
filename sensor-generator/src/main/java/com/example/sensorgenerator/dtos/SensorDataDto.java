package com.example.sensorgenerator.dtos;

import com.example.sensorgenerator.models.MeasurementType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Schema(description = "DTO representing a sensor data payload sent to the system.")
public class SensorDataDto {

  @NotNull(message = "Sensor ID must not be null")
  @Schema(
      description = "Unique identifier of the sensor",
      example = "101"
  )
  private Long sensorId;

  @NotNull(message = "Timestamp is required")
  @PastOrPresent(message = "Timestamp cannot be in the future")
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  @Schema(
      description = "Timestamp when the measurement was taken (ISO-8601 format)",
      example = "2025-08-12T10:15:30"
  )
  private LocalDateTime timestamp;

  @NotNull(message = "Measurement is required")
  @DecimalMin(value = "0.0", message = "Measurement must be non-negative")
  @Schema(
      description = "Measured value from the sensor",
      example = "23.5",
      minimum = "0.0"
  )
  private Double measurement;

  @NotNull(message = "Measurement type is required")
  @Schema(
      description = "Type of measurement (e.g., temperature, voltage, power)",
      example = "TEMPERATURE",
      implementation = MeasurementType.class
  )
  private MeasurementType measurementType;
}
