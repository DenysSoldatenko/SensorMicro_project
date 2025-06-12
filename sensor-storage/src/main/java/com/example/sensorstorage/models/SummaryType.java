package com.example.sensorstorage.models;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum representing types of statistical summaries for sensor data.
 */
public enum SummaryType {

  MIN("Minimum"),
  MAX("Maximum"),
  AVG("Average"),
  SUM("Sum");

  private final String label;

  SummaryType(String label) {
    this.label = label;
  }

  /**
   * Gets the human-readable label for the summary type.
   *
   * @return label such as "Minimum", "Maximum"
   */
  @JsonValue
  public String getLabel() {
    return label;
  }
}
