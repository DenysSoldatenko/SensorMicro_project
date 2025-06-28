package com.example.sensorconsumer.models;

import static jakarta.persistence.EnumType.STRING;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import lombok.Data;

/**
 * Represents a unit of sensor data.
 */
@Data
@Entity
@Table(name = "sensor_data")
@Schema(description = "Represents a single sensor measurement including its type, value, and timestamp")
public class SensorData {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(
      description = "Unique identifier of the record",
      example = "1"
  )
  private Long id;

  @Column(name = "sensor_id")
  @Schema(
      description = "Unique identifier of the sensor",
      example = "101"
  )
  private Long sensorId;

  @Column(name = "timestamp")
  @Schema(
      description = "Timestamp when the measurement was taken (ISO-8601 with timezone)",
      example = "2025-08-12T10:15:30Z"
  )
  private OffsetDateTime timestamp;

  @Column(name = "measurement")
  @Schema(
      description = "Measured value from the sensor",
      example = "23.5",
      minimum = "0.0"
  )
  private Double measurement;

  @Enumerated(STRING)
  @Column(name = "type")
  @Schema(
      description = "Type of measurement (e.g., temperature, voltage, power)",
      example = "TEMPERATURE",
      implementation = MeasurementType.class
  )
  private MeasurementType measurementType;
}
