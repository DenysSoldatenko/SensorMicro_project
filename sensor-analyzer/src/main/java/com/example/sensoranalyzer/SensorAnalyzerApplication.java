package com.example.sensoranalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Sensor Analyzer Spring Boot application.
 *
 * <p>This application processes sensor data, updates summaries, and stores
 * aggregated results in Redis according to business logic defined in the application.
 */
@SpringBootApplication
public class SensorAnalyzerApplication {

  /**
   * Main method that starts the Spring Boot application.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(SensorAnalyzerApplication.class, args);
  }

}
