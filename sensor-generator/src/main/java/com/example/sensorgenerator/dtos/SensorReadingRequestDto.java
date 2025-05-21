package com.example.sensorgenerator.dtos;

import com.example.sensorgenerator.models.MeasurementType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SensorReadingRequestDto {

  @NotNull(message = "Sensor ID must not be null")
  private Long sensorId;

  @NotNull(message = "Timestamp is required")
  @PastOrPresent(message = "Timestamp cannot be in the future")
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime timestamp;

  @NotNull(message = "Measurement is required")
  @DecimalMin(value = "0.0", message = "Measurement must be non-negative")
  private Double measurement;

  @NotNull(message = "Measurement type is required")
  private MeasurementType measurementType;
}
