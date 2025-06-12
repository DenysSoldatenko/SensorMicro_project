package com.example.sensorstorage.models;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a unit of sensor data.
 *
 * <p>This model is used to encapsulate information sent from a sensor device.
 * Includes validation constraints for API payload integrity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorData {

  @NotNull
  private Long sensorId;

  @PastOrPresent
  private LocalDateTime timestamp;

  @NotNull
  @DecimalMin("0.0")
  private Double measurement;

  @NotNull
  private MeasurementType measurementType;
}
