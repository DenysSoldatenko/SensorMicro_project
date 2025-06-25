package com.example.sensoranalyzer.utils;

import com.example.sensoranalyzer.models.MeasurementType;
import com.example.sensoranalyzer.models.SensorData;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.time.OffsetDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DebeziumPayloadMapper {

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
