package com.example.sensorproducer.configurations;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI 3 configuration for Sensor Generator microservice.
 * Exposes public, unauthenticated endpoints for sensor data ingestion and generation.
 */
@Configuration
public class SwaggerConfig {

  /**
   * Configures the OpenAPI documentation for the Sensor Generator API.
   *
   * @return the OpenAPI instance with basic API info and server definition
   */
  @Bean
  public OpenAPI sensorProducerOpenApi() {
    String serverUrl = "http://localhost:8081";
    return new OpenAPI()
      .info(apiInfo())
      .servers(List.of(
        new Server()
          .url(serverUrl)
          .description("Primary server for Sensor Producer API. Use this for local testing environments.")
      ))
      .components(new Components());
  }

  private Info apiInfo() {
    return new Info()
      .title("Sensor Producer API")
      .version("1.0.0")
      .description("""
        The **Sensor Producer API** sends **real-time sensor data** from IoT devices or generates **simulated data**
        for testing and analytics. It supports **Temperature**, **Voltage**, and **Power** measurements, enables
        **batch generation** for load testing, and delivers all data reliably to downstream systems via **Kafka**.
        All endpoints expect **JSON payloads** with **sensor ID**, **measurement type**, **value**, and **timestamp**.
        """)
      .termsOfService("https://example.com/terms")
      .contact(new Contact()
        .name("Denys Soldatenko")
        .email("support@example.com"));
  }
}
