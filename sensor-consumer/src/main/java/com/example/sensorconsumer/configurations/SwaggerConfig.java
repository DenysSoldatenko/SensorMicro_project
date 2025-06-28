package com.example.sensorconsumer.configurations;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger configuration for the Sensor Analyzer (Kafka Consumer) API.
 * Provides metadata and server info for OpenAPI documentation.
 */
@Configuration
public class SwaggerConfig {

  /**
   * Creates and configures the OpenAPI specification for the consumer API.
   *
   * @return OpenAPI specification instance.
   */
  @Bean
  public OpenAPI openApi() {
    String serverUrl = "http://localhost:8082";
    return new OpenAPI()
      .info(apiInfo())
      .servers(List.of(
        new Server()
          .url(serverUrl)
          .description("Primary server for Sensor Consumer API. Use this for local testing and monitoring environments.")
      ))
      .components(new Components());
  }

  private Info apiInfo() {
    return new Info()
      .title("Sensor Consumer API")
      .version("1.0.0")
      .description("""
        The **Sensor Consumer API** monitors and retrieves **real-time sensor data** consumed from Kafka topics.
        It supports **Temperature**, **Voltage**, and **Power** measurements, allows inspection of
        **recent sensor readings**, and provides endpoints for **analysis, debugging, and monitoring**.
        All data is returned in **JSON format** with **sensor ID**, **measurement type**, **value**, and **timestamp**.
        """)
      .termsOfService("https://example.com/terms")
      .contact(new Contact()
        .name("Denys Soldatenko")
        .email("support@example.com"));
  }
}
