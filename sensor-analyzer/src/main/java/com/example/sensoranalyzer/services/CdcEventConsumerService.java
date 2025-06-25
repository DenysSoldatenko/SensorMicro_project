package com.example.sensoranalyzer.services;

public interface CdcEventConsumerService<T> {

  void handle(T event);
}
