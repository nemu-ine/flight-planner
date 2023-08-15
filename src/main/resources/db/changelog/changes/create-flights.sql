--liquibase formatted sql

--changeset aaaa:2

CREATE TABLE flights (
    id SERIAL PRIMARY KEY,
    from_id INT REFERENCES airports (id) ON DELETE CASCADE,
    to_id INT REFERENCES airports (id) ON DELETE CASCADE,
    carrier VARCHAR(100) NOT NULL,
    departure TIMESTAMP NOT NULL,
    arrival TIMESTAMP NOT NULL
)