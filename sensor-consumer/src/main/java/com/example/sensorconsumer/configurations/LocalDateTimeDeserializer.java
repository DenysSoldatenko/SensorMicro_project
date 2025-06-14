package com.example.sensorconsumer.configurations;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

/**
 * Custom Gson deserializer for {@link LocalDateTime}.
 * Expects JSON array format: [year, month, day, hour, minute, second]
 */
@Component
public class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {

  private static final int REQUIRED_COMPONENT_COUNT = 6;

  @Override
  public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonArray jsonArray = validateAndExtractArray(json);
    return parseLocalDateTime(jsonArray);
  }

  /**
   * Validates that the incoming JSON element is a correctly sized array.
   *
   * @param json the JSON element
   * @return the JSON array if valid
   * @throws JsonParseException if the format is invalid
   */
  private JsonArray validateAndExtractArray(JsonElement json) {
    if (!json.isJsonArray()) {
      throw new JsonParseException("Expected a JSON array to deserialize LocalDateTime");
    }

    JsonArray array = json.getAsJsonArray();

    if (array.size() != REQUIRED_COMPONENT_COUNT) {
      throw new JsonParseException("Expected exactly 6 elements for LocalDateTime [yyyy, MM, dd, HH, mm, ss], but got " + array.size());
    }

    return array;
  }

  /**
   * Converts a validated JSON array into a {@link LocalDateTime} object.
   *
   * @param array the validated JSON array
   * @return the LocalDateTime instance
   * @throws JsonParseException if parsing fails
   */
  private LocalDateTime parseLocalDateTime(JsonArray array) {
    try {
      return LocalDateTime.of(
        array.get(0).getAsInt(), // year
        array.get(1).getAsInt(), // month
        array.get(2).getAsInt(), // day
        array.get(3).getAsInt(), // hour
        array.get(4).getAsInt(), // minute
        array.get(5).getAsInt()  // second
      );
    } catch (Exception ex) {
      throw new JsonParseException("Invalid values in LocalDateTime array: " + ex.getMessage(), ex);
    }
  }
}
