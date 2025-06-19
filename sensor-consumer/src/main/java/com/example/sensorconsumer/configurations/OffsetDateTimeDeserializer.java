package com.example.sensorconsumer.configurations;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import org.springframework.stereotype.Component;

/**
 * Custom Gson deserializer for {@link OffsetDateTime}.
 * Expects ISO-8601 format with timezone, e.g. 2025-08-12T10:15:30Z
 */
@Component
public class OffsetDateTimeDeserializer implements JsonDeserializer<OffsetDateTime> {

  @Override
  public OffsetDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

    if (!json.isJsonPrimitive() || !json.getAsJsonPrimitive().isString()) {
      throw new JsonParseException("Expected ISO-8601 timestamp string for OffsetDateTime");
    }

    try {
      return OffsetDateTime.parse(json.getAsString());
    } catch (DateTimeParseException ex) {
      throw new JsonParseException("Invalid ISO-8601 OffsetDateTime value: " + json.getAsString(), ex);
    }
  }
}
