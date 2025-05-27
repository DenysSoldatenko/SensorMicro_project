package com.example.sensorgenerator.mappers;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.ReportingPolicy.ERROR;

import com.example.sensorgenerator.dtos.SensorDataDto;
import com.example.sensorgenerator.models.SensorData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR, unmappedTargetPolicy = ERROR)
public interface DataMapper {

  SensorData toEntity(SensorDataDto dto);

  SensorDataDto toDto(SensorData entity);
}
