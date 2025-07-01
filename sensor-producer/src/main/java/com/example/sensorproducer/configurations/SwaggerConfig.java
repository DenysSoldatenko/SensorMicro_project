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
 * Swagger configuration for the Sensor Producer API.
 * Provides metadata and server info for OpenAPI documentation.
 */
@Configuration
public class SwaggerConfig {

  /**
   * Creates and configures the OpenAPI specification for the Sensor Producer API.
   *
   * @return OpenAPI specification instance.
   */
  @Bean
  public OpenAPI openApi() {
    String serverUrl = "http://localhost:8081";
    return new OpenAPI()
      .info(apiInfo())
      .servers(List.of(
        new Server()
          .url(serverUrl)
          .description("Primary server for Sensor Producer API. Use this for local testing or monitoring")
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
