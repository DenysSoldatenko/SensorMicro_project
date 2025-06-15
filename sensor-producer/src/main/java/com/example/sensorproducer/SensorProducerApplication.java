package com.example.sensorproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Entry point for the Sensor Producer Spring Boot application.
 *
 * <p>This service is responsible for generating sensor data,
 * and sending it to Kafka topics based on measurement types.
 *
 * <p>Async capabilities are enabled for non-blocking Kafka publishing and batch operations.
 */
@EnableAsync
@SpringBootApplication
public class SensorProducerApplication {

  /**
   * Main method that starts the Spring Boot application.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(SensorProducerApplication.class, args);
  }

}
