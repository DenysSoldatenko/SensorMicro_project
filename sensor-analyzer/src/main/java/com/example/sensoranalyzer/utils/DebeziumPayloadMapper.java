package com.example.sensoranalyzer.utils;

import com.example.sensoranalyzer.models.MeasurementType;
import com.example.sensoranalyzer.models.SensorData;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.time.OffsetDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Utility component for mapping Debezium CDC JSON payloads to {@link SensorData} objects.
 *
 * <p>This class parses the Debezium message structure, extracts the payload fields,
 * and converts them into a domain {@link SensorData} instance.
 */
@Slf4j
@Component
public class DebeziumPayloadMapper {

  /**
   * Maps a Debezium CDC message (JSON string) to a {@link SensorData} object.
   *
   * @param message the raw JSON message from Debezium; must not be {@code null} or empty
   * @return a {@link SensorData} object populated from the payload
   * @throws IllegalArgumentException if the payload is missing or required fields are invalid
   * @throws com.google.gson.JsonSyntaxException if the message is not valid JSON
   */
  public SensorData map(String message) {
    JsonObject root = JsonParser.parseString(message).getAsJsonObject();
    JsonObject payload = root.getAsJsonObject("payload");

    if (payload == null) {
      throw new IllegalArgumentException("Debezium payload is missing");
    }

    SensorData data = new SensorData();
    data.setSensorId(payload.get("sensor_id").getAsLong());
    data.setMeasurement(payload.get("measurement").getAsDouble());
    data.setMeasurementType(MeasurementType.valueOf(payload.get("type").getAsString()));
    data.setTimestamp(OffsetDateTime.parse(payload.get("timestamp").getAsString()));

    return data;
  }
}
