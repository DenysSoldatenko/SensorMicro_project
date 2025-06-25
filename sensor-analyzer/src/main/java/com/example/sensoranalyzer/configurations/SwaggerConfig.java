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
    return new OpenAPI()
      .info(apiInfo())
      .servers(List.of(new Server().url("http://localhost:8080").description("Sensor Analyzer API Server")))
      .components(new Components());
  }

  private Info apiInfo() {
    return new Info()
      .title("Sensor Analyzer API")
      .version("1.0.0")
      .description("""
            This API provides analytics and statistical summaries for sensor data.
            It allows querying summary statistics (e.g., MIN, MAX, AVG) per sensor,
            filtered by measurement type (e.g., temperature, humidity, voltage).
            
            Suitable for internal monitoring, diagnostics, or downstream processing of sensor events.
            """)
      .termsOfService("https://example.com/terms")
      .contact(new Contact()
        .name("Denys Soldatenko")
        .email("support@example.com"));
  }
}
