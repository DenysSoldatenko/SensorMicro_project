package com.example.sensoranalyzer.mappers;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.ReportingPolicy.ERROR;

import com.example.sensoranalyzer.dtos.SummaryDataDto;
import com.example.sensoranalyzer.dtos.SummaryDataDto.SummaryEntryDto;
import com.example.sensoranalyzer.models.SummaryData;
import com.example.sensoranalyzer.models.SummaryData.SummaryEntry;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between {@link SummaryDataDto} and {@link SummaryData} entities.
 *
 * <p>Uses MapStruct for compile-time mapping generation.
 *
 * <p>Configuration:
 * <ul>
 *   <li>{@code componentModel = "spring"}: Enables Spring component scanning.</li>
 *   <li>{@code injectionStrategy = CONSTRUCTOR}: Uses constructor injection for dependencies.</li>
 *   <li>{@code unmappedTargetPolicy = ERROR}: Fails compilation if any mapping is incomplete.</li>
 * </ul>
 */
@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR, unmappedTargetPolicy = ERROR)
public interface SummaryMapper {

  /**
   * Converts a {@link SummaryDataDto} to a {@link SummaryData} entity.
   *
   * @param dto the DTO to convert; must not be {@code null}
   * @return the corresponding {@link SummaryData} entity
   */
  SummaryData toEntity(SummaryDataDto dto);

  /**
   * Converts a {@link SummaryEntryDto} to a {@link SummaryEntry} entity.
   *
   * @param dto the DTO to convert; must not be {@code null}
   * @return the corresponding {@link SummaryEntry} entity
   */
  SummaryEntry toEntity(SummaryEntryDto dto);

  /**
   * Converts a list of {@link SummaryEntryDto} objects to a list of {@link SummaryEntry} entities.
   *
   * @param dtos the list of DTOs to convert; must not be {@code null}
   * @return the list of corresponding {@link SummaryEntry} entities
   */
  List<SummaryEntry> toEntity(List<SummaryEntryDto> dtos);

  /**
   * Converts a {@link SummaryData} entity to a {@link SummaryDataDto}.
   *
   * @param entity the entity to convert; must not be {@code null}
   * @return the corresponding {@link SummaryDataDto}
   */
  SummaryDataDto toDto(SummaryData entity);

  /**
   * Converts a {@link SummaryEntry} entity to a {@link SummaryEntryDto}.
   *
   * @param entity the entity to convert; must not be {@code null}
   * @return the corresponding {@link SummaryEntryDto}
   */
  SummaryEntryDto toDto(SummaryEntry entity);

  /**
   * Converts a list of {@link SummaryEntry} entities to a list of {@link SummaryEntryDto}.
   *
   * @param entities the list of entities to convert; must not be {@code null}
   * @return the list of corresponding {@link SummaryEntryDto} objects
   */
  List<SummaryEntryDto> toDto(List<SummaryEntry> entities);
}
