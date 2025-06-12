package com.example.sensorstorage.mappers;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.ReportingPolicy.ERROR;

import com.example.sensorstorage.dtos.SummaryDto;
import com.example.sensorstorage.models.SummaryData;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between {@link SummaryDto} and {@link SummaryData} entities.
 *
 * <p>Uses MapStruct for compile-time mapping generation.
 *
 * <p>Configuration:
 *
 *<p>- {@code componentModel = "spring"}: Enables Spring component scanning.
 *
 *<p>- {@code injectionStrategy = CONSTRUCTOR}: Uses constructor injection for dependencies.
 *
 *<p>- {@code unmappedTargetPolicy = ERROR}: Fails compilation if any mapping is incomplete.
 */
@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR, unmappedTargetPolicy = ERROR)
public interface SummaryMapper {

  /**
   * Converts a {@link SummaryDto} to a {@link SummaryData} entity.
   *
   * @param dto the source DTO
   * @return the mapped entity
   */
  SummaryData toEntity(SummaryDto dto);

  /**
   * Converts a {@link SummaryData} entity to a {@link SummaryDto}.
   *
   * @param entity the source entity
   * @return the mapped DTO
   */
  SummaryDto toDto(SummaryData entity);
}
