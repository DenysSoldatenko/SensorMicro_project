package com.example.sensoranalyzer.services;

/**
 * Generic interface for consuming CDC (Change Data Capture) events.
 *
 * <p>This interface defines a contract for handling events of type {@code T}.
 * Implementations are responsible for processing events, mapping payloads,
 * or updating downstream systems as required.
 *
 * @param <T> the type of the event to be consumed
 */
public interface CdcEventConsumerService<T> {

  /**
   * Handles a single CDC event.
   *
   * @param event the event to handle; must not be {@code null}
   */
  void handle(T event);
}
