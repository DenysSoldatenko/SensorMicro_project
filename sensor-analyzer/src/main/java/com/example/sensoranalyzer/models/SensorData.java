package com.example.sensoranalyzer.models;

import static jakarta.persistence.EnumType.STRING;

import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * Represents a unit of sensor data.
 *
 * <p>This model is used to encapsulate information sent from a sensor device.
 * Includes validation constraints for API payload integrity.
 */
@Data
@Table(name = "sensor_data")
public class SensorData {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "Sensor ID must not be null")
  @Column(name = "sensor_id", nullable = false)
  private Long sensorId;

  @NotNull(message = "Timestamp must be provided")
  @PastOrPresent(message = "Timestamp must be in the past or present")
  @Column(name = "timestamp", nullable = false)
  private LocalDateTime timestamp;

  @NotNull(message = "Measurement must not be null")
  @DecimalMin(value = "0.0", message = "Measurement must be non-negative")
  @Column(name = "measurement", nullable = false)
  private Double measurement;

  @Enumerated(STRING)
  @NotNull(message = "Measurement type must not be null")
  @Column(name = "type", nullable = false, length = 50)
  private MeasurementType measurementType;
}
