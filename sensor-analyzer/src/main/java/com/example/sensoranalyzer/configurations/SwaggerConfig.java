package com.example.sensoranalyzer.configurations;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger configuration for the Sensor Analyzer API.
 * Provides metadata and server info for OpenAPI documentation.
 */
@Configuration
public class SwaggerConfig {

  /**
   * Creates and configures the OpenAPI specification for the Sensor Analyzer API.
   *
   * @return OpenAPI specification instance.
   */
  @Bean
  public OpenAPI openApi() {
    String serverUrl = "http://localhost:8080";
    return new OpenAPI()
      .info(apiInfo())
      .servers(List.of(
        new Server()
          .url(serverUrl)
          .description("Primary server for Sensor Analyzer API. Use this for local testing or monitoring")
      ))
      .components(new Components());
  }

  private Info apiInfo() {
    return new Info()
      .title("Sensor Analyzer API")
      .version("1.0.0")
      .description("""
        The **Sensor Analyzer API** provides **real-time analytics** and **summary statistics** for sensor data.
        It supports **Temperature**, **Voltage**, **Power**, and other measurements and allows querying **MIN**,
        **MAX**, **SUM**, and **AVG** values per sensor or measurement type. All endpoints return data in **JSON format**
        and are intended for **monitoring**, **diagnostics**, or **downstream processing** of sensor events.
        """)
      .termsOfService("https://example.com/terms")
      .contact(new Contact()
        .name("Denys Soldatenko")
        .email("support@example.com"));
  }
}
