package com.example.sensorconsumer.models;

import static jakarta.persistence.EnumType.STRING;

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
public class SensorData {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "sensor_id")
  private Long sensorId;

  @Column(name = "timestamp")
  private OffsetDateTime timestamp;

  @Column(name = "measurement")
  private Double measurement;

  @Enumerated(STRING)
  @Column(name = "type")
  private MeasurementType measurementType;
}
