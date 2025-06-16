package com.example.sensoranalyzer.controllers;

import static java.util.Collections.emptySet;

import com.example.sensoranalyzer.dtos.SummaryDataDto;
import com.example.sensoranalyzer.mappers.SummaryMapper;
import com.example.sensoranalyzer.models.MeasurementType;
import com.example.sensoranalyzer.models.SummaryType;
import com.example.sensoranalyzer.services.SummaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing sensor analytics operations including data summaries and statistics.
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/analytics")
@Tag(name = "Analytics Controller", description = "Sensor analytics and summary endpoints")
public class AnalyticsController {

  private final SummaryMapper summaryMapper;
  private final SummaryService summaryService;

  /**
   * Retrieves a summary for a specific sensor, optionally filtered by measurement and summary types.
   *
   * @param sensorId         the ID of the sensor
   * @param measurementTypes optional filter by measurement types (e.g., TEMPERATURE)
   * @param summaryTypes     optional filter by summary types (e.g., AVG, MAX)
   * @return summary DTO containing aggregated values
   */
  @GetMapping("/summary/{sensorId}")
  @Operation(
      summary = "Get sensor data summary",
      description = "Returns aggregated summary data for a sensor. You can optionally filter by measurement types and summary types."
  )
  public SummaryDataDto getSummary(
      @PathVariable
      @Min(value = 1, message = "Sensor ID must be greater than 0")
      long sensorId,

      @RequestParam(name = "mt", required = false)
      @Parameter(description = "Filter by measurement types (e.g., TEMPERATURE, HUMIDITY)")
      Set<MeasurementType> measurementTypes,

      @RequestParam(name = "st", required = false)
      @Parameter(description = "Filter by summary types (e.g., AVG, MAX, MIN)")
      Set<SummaryType> summaryTypes
  ) {
    log.info(
        "Fetching summary | sensorId={} | measurementTypes={} | summaryTypes={}",
        sensorId,
        measurementTypes != null ? measurementTypes : "ALL",
        summaryTypes != null ? summaryTypes : "ALL"
    );

    var summary = summaryService.getSummaryForSensor(
        sensorId,
        measurementTypes != null ? measurementTypes : emptySet(),
        summaryTypes != null ? summaryTypes : emptySet()
    );

    return summaryMapper.toDto(summary);
  }
}
