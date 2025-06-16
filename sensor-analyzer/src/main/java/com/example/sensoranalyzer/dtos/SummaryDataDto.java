package com.example.sensoranalyzer.dtos;

import com.example.sensoranalyzer.models.MeasurementType;
import com.example.sensoranalyzer.models.SummaryType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Singular;

/**
 * DTO representing aggregated summary data for a sensor,
 * grouped by measurement type and summary statistics.
 */
@Builder
public record SummaryDataDto(long sensorId, @Singular("entry") Map<MeasurementType, List<SummaryEntryDto>> values) {

  /**
   * DTO representation of a summary entry (e.g., MIN, MAX).
   */
  public record SummaryEntryDto(SummaryType type, double value, long counter) {
    @JsonCreator
    public SummaryEntryDto(
        @JsonProperty("type") SummaryType type,
        @JsonProperty("value") double value,
        @JsonProperty("counter") long counter
    ) {
      this.type = type;
      this.value = value;
      this.counter = counter;
    }
  }

  @JsonCreator
  public SummaryDataDto(
      @JsonProperty("sensorId") long sensorId,
      @JsonProperty("values") Map<MeasurementType, List<SummaryEntryDto>> values
  ) {
    this.sensorId = sensorId;
    this.values = values != null ? new EnumMap<>(values) : new EnumMap<>(MeasurementType.class);
  }
}
