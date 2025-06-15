package com.example.sensorproducer.utils;

import static java.lang.Math.round;
import static java.time.LocalDateTime.now;
import static java.util.concurrent.ThreadLocalRandom.current;

import com.example.sensorproducer.models.MeasurementType;
import com.example.sensorproducer.models.SensorData;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import lombok.experimental.UtilityClass;

/**
 * Utility class for generating randomized {@link SensorData} instances.
 */
@UtilityClass
public final class SensorDataFactory {

  private static final long SENSOR_ID_BOUND = 1000L;
  private static final double MIN_MEASUREMENT = 0.0;
  private static final double MAX_MEASUREMENT = 100.0;

  private static final ThreadLocalRandom RANDOM = current();

  /**
   * Generates a list of random SensorData entries.
   *
   * @param count the number of entries to generate
   * @return list of SensorData objects
   */
  public static List<SensorData> generateRandomBatch(int count) {
    return IntStream.range(0, count)
      .mapToObj(i -> generateRandomSensorData())
      .toList();
  }

  /**
   * Generates a single randomized SensorData object.
   *
   * @return SensorData instance
   */
  public static SensorData generateRandomSensorData() {
    return new SensorData(
      RANDOM.nextLong(SENSOR_ID_BOUND),
      now().withNano(0),
      round(RANDOM.nextDouble(MIN_MEASUREMENT, MAX_MEASUREMENT) * 10.0) / 10.0,
      randomMeasurementType()
    );
  }

  private static MeasurementType randomMeasurementType() {
    MeasurementType[] types = MeasurementType.values();
    return types[RANDOM.nextInt(types.length)];
  }
}
