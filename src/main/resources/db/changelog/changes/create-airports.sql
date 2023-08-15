--liquibase formatted sql

--changeset aaaa:1

CREATE TABLE airports (
    id SERIAL PRIMARY KEY,
    country VARCHAR(70) NOT NULL,
    city VARCHAR(50) NOT NULL,
    airport VARCHAR(100) NOT NULL
);