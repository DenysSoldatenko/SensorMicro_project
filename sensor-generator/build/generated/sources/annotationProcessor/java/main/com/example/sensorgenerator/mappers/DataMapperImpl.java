package com.example.sensorgenerator.mappers;

import com.example.sensorgenerator.dtos.SensorDataDto;
import com.example.sensorgenerator.models.SensorData;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-13T10:28:13+0300",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class DataMapperImpl implements DataMapper {

    @Override
    public SensorData toEntity(SensorDataDto dto) {
        if ( dto == null ) {
            return null;
        }

        SensorData sensorData = new SensorData();

        sensorData.setSensorId( dto.getSensorId() );
        sensorData.setTimestamp( dto.getTimestamp() );
        sensorData.setMeasurement( dto.getMeasurement() );
        sensorData.setMeasurementType( dto.getMeasurementType() );

        return sensorData;
    }

    @Override
    public SensorDataDto toDto(SensorData entity) {
        if ( entity == null ) {
            return null;
        }

        SensorDataDto sensorDataDto = new SensorDataDto();

        sensorDataDto.setSensorId( entity.getSensorId() );
        sensorDataDto.setTimestamp( entity.getTimestamp() );
        sensorDataDto.setMeasurement( entity.getMeasurement() );
        sensorDataDto.setMeasurementType( entity.getMeasurementType() );

        return sensorDataDto;
    }
}
