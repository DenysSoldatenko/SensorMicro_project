package com.example.sensorgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Entry point for the Sensor Generator Spring Boot application.
 *
 * <p>This service is responsible for generating or receiving sensor data,
 * and sending it to Kafka topics based on measurement types.
 *
 * <p>Async capabilities are enabled for non-blocking Kafka publishing and batch operations.
 */
@EnableAsync
@SpringBootApplication
public class SensorGeneratorApplication {

  /**
   * Main method that starts the Spring Boot application.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(SensorGeneratorApplication.class, args);
  }

}
