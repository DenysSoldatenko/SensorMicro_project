package com.example.sensorconsumer.repositories;

import com.example.sensorconsumer.models.SensorData;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing sensor data stored in the database.
 * Provides methods to fetch the most recent sensor readings, optionally filtered by type.
 */
@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {

  /**
   * Retrieves the most recent sensor data entries for a specific measurement type.
   *
   * @param type  the measurement type (e.g., TEMPERATURE, VOLTAGE, POWER)
   * @param limit the maximum number of records to retrieve
   * @return a list of recent sensor data entries filtered by type
   */
  @Query(value = """
      SELECT * FROM sensor_data
      WHERE type = :type
      ORDER BY timestamp DESC
      LIMIT :limit
      """, nativeQuery = true)
  List<SensorData> findRecentByTypeLimited(String type, int limit);

  /**
   * Retrieves the most recent sensor data entries across all measurement types.
   *
   * @param limit the maximum number of records to retrieve
   * @return a list of recent sensor data entries
   */
  @Query(value = """
      SELECT * FROM sensor_data
      ORDER BY timestamp DESC
      LIMIT :limit
      """, nativeQuery = true)
  List<SensorData> findRecentAllLimited(int limit);
}