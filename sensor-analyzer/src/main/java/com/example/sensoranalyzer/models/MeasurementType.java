package com.example.sensoranalyzer.models;

/**
 * Enum representing supported types of sensor measurements.
 * Each type corresponds to a Kafka topic for routing purposes.
 */
public enum MeasurementType {
    TEMPERATURE,
    VOLTAGE,
    POWER
}
