package com.example.sensorproducer.mappers;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.ReportingPolicy.ERROR;

import com.example.sensorproducer.dtos.SensorDataDto;
import com.example.sensorproducer.models.SensorData;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between {@link SensorDataDto} and {@link SensorData} entities.
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
public interface DataMapper {

  /**
   * Converts a {@link SensorDataDto} to a {@link SensorData} entity.
   *
   * @param dto the source DTO
   * @return the mapped entity
   */
  SensorData toEntity(SensorDataDto dto);

  /**
   * Converts a {@link SensorData} entity to a {@link SensorDataDto}.
   *
   * @param entity the source entity
   * @return the mapped DTO
   */
  SensorDataDto toDto(SensorData entity);
}
