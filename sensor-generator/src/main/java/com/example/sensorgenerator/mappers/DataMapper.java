package com.example.sensorgenerator.mappers;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.ReportingPolicy.ERROR;

import com.example.sensorgenerator.dtos.SensorReadingRequestDto;
import com.example.sensorgenerator.models.SensorReading;
import org.mapstruct.Mapper;

@Mapper(
  componentModel = "spring",
  injectionStrategy = CONSTRUCTOR,
  unmappedTargetPolicy = ERROR
)
public interface DataMapper {

  SensorReading toEntity(SensorReadingRequestDto dto);

  SensorReadingRequestDto toDto(SensorReading entity);
}
