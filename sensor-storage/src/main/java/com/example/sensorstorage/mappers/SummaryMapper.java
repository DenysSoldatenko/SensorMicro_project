package com.example.sensorstorage.mappers;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.ReportingPolicy.ERROR;

import com.example.sensorstorage.dtos.SummaryDataDto;
import com.example.sensorstorage.dtos.SummaryDataDto.SummaryEntryDto;
import com.example.sensorstorage.models.SummaryData;
import com.example.sensorstorage.models.SummaryData.SummaryEntry;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between {@link SummaryDataDto} and {@link SummaryData} entities.
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

  SummaryData toEntity(SummaryDataDto dto);

  SummaryEntry toEntity(SummaryEntryDto dto);

  List<SummaryEntry> toEntity(List<SummaryEntryDto> dtos);

  SummaryDataDto toDto(SummaryData entity);

  SummaryEntryDto toDto(SummaryEntry entity);

  List<SummaryEntryDto> toDto(List<SummaryEntry> entities);
}
