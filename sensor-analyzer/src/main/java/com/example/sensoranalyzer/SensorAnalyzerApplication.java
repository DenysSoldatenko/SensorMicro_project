package com.example.sensoranalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Sensor Analyzer Spring Boot application.
 *
 * <p>This application consumes sensor data from a Kafka topic and processes it
 * according to business logic defined in the application.
 */
@SpringBootApplication
public class SensorAnalyzerApplication {

  /**
   * Main method that launches the Spring Boot application.
   *
   * @param args command-line arguments passed to the application
   */
  public static void main(String[] args) {
    SpringApplication.run(SensorAnalyzerApplication.class, args);
  }
}
