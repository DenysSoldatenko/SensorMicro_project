package com.example.sensoranalyzer.repositories;

import com.example.sensoranalyzer.models.SensorData;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {

  @Query(value = """
      SELECT * FROM sensor_data
      WHERE type = :type
      ORDER BY timestamp DESC
      LIMIT :limit
      """, nativeQuery = true)
  List<SensorData> findRecentByTypeLimited(String type, int limit);

  @Query(value = """
      SELECT * FROM sensor_data
      ORDER BY timestamp DESC
      LIMIT :limit
      """, nativeQuery = true)
  List<SensorData> findRecentAllLimited(int limit);
}