package com.example.sensorgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SensorGeneratorApplication {

  public static void main(String[] args) {
    SpringApplication.run(SensorGeneratorApplication.class, args);
  }

}
