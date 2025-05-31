package com.example.sensoranalyzer.configurations;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openApi() {
    return new OpenAPI()
      .info(apiInfo())
      .servers(List.of(new Server().url("http://localhost:8082").description("Primary API Server")))
      .components(new Components()); // No auth components for public API
  }

  private Info apiInfo() {
    return new Info()
      .title("Sensor Generator API")
      .version("1.0.0")
      .description("""
        This API allows external systems to send or simulate IoT sensor data.
        It supports real-time data ingestion and random data generation across supported measurement types
        such as temperature, voltage, and power.
        """)
      .termsOfService("https://example.com/terms")
      .contact(new Contact()
        .name("Denys Soldatenko")
        .email("support@example.com"));
  }
}
