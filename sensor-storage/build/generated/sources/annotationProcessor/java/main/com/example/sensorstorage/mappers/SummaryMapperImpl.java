package com.example.sensorstorage.mappers;

import com.example.sensorstorage.dtos.SummaryDataDto;
import com.example.sensorstorage.models.MeasurementType;
import com.example.sensorstorage.models.SummaryData;
import com.example.sensorstorage.models.SummaryType;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-30T10:18:28+0300",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class SummaryMapperImpl implements SummaryMapper {

    @Override
    public SummaryData toEntity(SummaryDataDto dto) {
        if ( dto == null ) {
            return null;
        }

        long sensorId = 0L;

        sensorId = dto.sensorId();

        SummaryData summaryData = new SummaryData( sensorId );

        if ( summaryData.getValues() != null ) {
            Map<MeasurementType, List<SummaryData.SummaryEntry>> map = measurementTypeListMapToMeasurementTypeListMap( dto.values() );
            if ( map != null ) {
                summaryData.getValues().putAll( map );
            }
        }

        return summaryData;
    }

    @Override
    public SummaryData.SummaryEntry toEntity(SummaryDataDto.SummaryEntryDto dto) {
        if ( dto == null ) {
            return null;
        }

        SummaryType type = null;
        double value = 0.0d;
        long counter = 0L;

        type = dto.type();
        value = dto.value();
        counter = dto.counter();

        SummaryData.SummaryEntry summaryEntry = new SummaryData.SummaryEntry( type, value, counter );

        return summaryEntry;
    }

    @Override
    public List<SummaryData.SummaryEntry> toEntity(List<SummaryDataDto.SummaryEntryDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<SummaryData.SummaryEntry> list = new ArrayList<SummaryData.SummaryEntry>( dtos.size() );
        for ( SummaryDataDto.SummaryEntryDto summaryEntryDto : dtos ) {
            list.add( toEntity( summaryEntryDto ) );
        }

        return list;
    }

    @Override
    public SummaryDataDto toDto(SummaryData entity) {
        if ( entity == null ) {
            return null;
        }

        SummaryDataDto.SummaryDataDtoBuilder summaryDataDto = SummaryDataDto.builder();

        summaryDataDto.sensorId( entity.getSensorId() );
        summaryDataDto.values( measurementTypeListMapToMeasurementTypeListMap1( entity.getValues() ) );

        return summaryDataDto.build();
    }

    @Override
    public SummaryDataDto.SummaryEntryDto toDto(SummaryData.SummaryEntry entity) {
        if ( entity == null ) {
            return null;
        }

        SummaryType type = null;
        double value = 0.0d;
        long counter = 0L;

        type = entity.type();
        value = entity.value();
        counter = entity.counter();

        SummaryDataDto.SummaryEntryDto summaryEntryDto = new SummaryDataDto.SummaryEntryDto( type, value, counter );

        return summaryEntryDto;
    }

    @Override
    public List<SummaryDataDto.SummaryEntryDto> toDto(List<SummaryData.SummaryEntry> entities) {
        if ( entities == null ) {
            return null;
        }

        List<SummaryDataDto.SummaryEntryDto> list = new ArrayList<SummaryDataDto.SummaryEntryDto>( entities.size() );
        for ( SummaryData.SummaryEntry summaryEntry : entities ) {
            list.add( toDto( summaryEntry ) );
        }

        return list;
    }

    protected Map<MeasurementType, List<SummaryData.SummaryEntry>> measurementTypeListMapToMeasurementTypeListMap(Map<MeasurementType, List<SummaryDataDto.SummaryEntryDto>> map) {
        if ( map == null ) {
            return null;
        }

        Map<MeasurementType, List<SummaryData.SummaryEntry>> map1 = new LinkedHashMap<MeasurementType, List<SummaryData.SummaryEntry>>( Math.max( (int) ( map.size() / .75f ) + 1, 16 ) );

        for ( java.util.Map.Entry<MeasurementType, List<SummaryDataDto.SummaryEntryDto>> entry : map.entrySet() ) {
            MeasurementType key = entry.getKey();
            List<SummaryData.SummaryEntry> value = toEntity( entry.getValue() );
            map1.put( key, value );
        }

        return map1;
    }

    protected Map<MeasurementType, List<SummaryDataDto.SummaryEntryDto>> measurementTypeListMapToMeasurementTypeListMap1(Map<MeasurementType, List<SummaryData.SummaryEntry>> map) {
        if ( map == null ) {
            return null;
        }

        Map<MeasurementType, List<SummaryDataDto.SummaryEntryDto>> map1 = new LinkedHashMap<MeasurementType, List<SummaryDataDto.SummaryEntryDto>>( Math.max( (int) ( map.size() / .75f ) + 1, 16 ) );

        for ( java.util.Map.Entry<MeasurementType, List<SummaryData.SummaryEntry>> entry : map.entrySet() ) {
            MeasurementType key = entry.getKey();
            List<SummaryDataDto.SummaryEntryDto> value = toDto( entry.getValue() );
            map1.put( key, value );
        }

        return map1;
    }
}
