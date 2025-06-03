package com.example.sensoranalyzer.utils;

import static java.util.Collections.unmodifiableList;

import com.example.sensoranalyzer.models.MeasurementType;
import com.example.sensoranalyzer.models.SensorData;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;
import org.springframework.stereotype.Component;

/**
 * A thread-safe storage component for maintaining a bounded history of sensor data messages
 * organized by measurement type.
 *
 * <p>This class manages in-memory storage of sensor data using a map of {@link MeasurementType}
 * to bounded {@link ConcurrentLinkedDeque}s. It ensures thread safety for concurrent access
 * (e.g., from Kafka listeners or REST controllers) and provides unmodifiable views of the data
 * to prevent external modification.</p>
 */
@Component
public class SensorDataStore {

  private static final int MAX_MESSAGES = 5;
  private final Map<MeasurementType, ConcurrentLinkedDeque<SensorData>> messageQueues;

  /**
   * Initializes the sensor data store with an empty queue for each measurement type.
   *
   * <p>Uses an {@link EnumMap} for efficient key lookup and type safety, and
   * {@link ConcurrentLinkedDeque} for thread-safe operations without explicit
   * synchronization.</p>
   */
  public SensorDataStore() {
    this.messageQueues = new EnumMap<>(MeasurementType.class);
    for (MeasurementType type : MeasurementType.values()) {
      messageQueues.put(type, new ConcurrentLinkedDeque<>());
    }
  }

  /**
   * Adds a sensor data message to the queue for the specified measurement type.
   *
   * <p>If the queue size exceeds {@link #MAX_MESSAGES}, the oldest message is removed
   * to maintain the bounded size. This operation is thread-safe.</p>
   *
   * @param type the measurement type (e.g., TEMPERATURE, VOLTAGE)
   * @param data the sensor data to add
   * @throws NullPointerException if type or data is null
   */
  public void addMessage(MeasurementType type, SensorData data) {
    if (type == null || data == null) {
      throw new NullPointerException("Measurement type and sensor data must not be null");
    }
    ConcurrentLinkedDeque<SensorData> deque = messageQueues.get(type);
    if (deque.size() >= MAX_MESSAGES) {
      deque.removeFirst();
    }
    deque.addLast(data);
  }

  /**
   * Retrieves an unmodifiable list of the most recent messages for the specified measurement type.
   *
   * <p>Returns a defensive copy to prevent external modification of the internal state,
   * ensuring immutability and thread safety for clients.</p>
   *
   * @param type the measurement type to query
   * @return an unmodifiable list of sensor data messages
   * @throws NullPointerException if type is null
   */
  public List<SensorData> getLastMessagesByType(MeasurementType type) {
    if (type == null) {
      throw new NullPointerException("Measurement type must not be null");
    }
    return unmodifiableList(new ArrayList<>(messageQueues.get(type)));
  }

  /**
   * Retrieves an unmodifiable list of all recent messages across all measurement types.
   *
   * <p>Combines messages from all queues into a single list, returned as an unmodifiable
   * defensive copy to ensure thread safety and immutability.</p>
   *
   * @return an unmodifiable list of all sensor data messages
   */
  public List<SensorData> getAllLastMessages() {
    List<SensorData> allMessages = new ArrayList<>();
    for (ConcurrentLinkedDeque<SensorData> deque : messageQueues.values()) {
      allMessages.addAll(deque);
    }
    return unmodifiableList(allMessages);
  }
}
