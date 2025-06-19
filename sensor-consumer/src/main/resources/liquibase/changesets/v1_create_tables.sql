CREATE TABLE sensor_data
(
    id          BIGSERIAL         PRIMARY KEY,
    sensor_id   BIGINT            NOT NULL,
    measurement DOUBLE PRECISION  NOT NULL,
    type        VARCHAR(50)       NOT NULL,
    timestamp   TIMESTAMPTZ       NOT NULL
);
