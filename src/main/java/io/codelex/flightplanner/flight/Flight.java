package io.codelex.flightplanner.flight;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Flight {

    private long id;
    @NotNull
    @Valid
    private Airport from;
    @NotNull
    @Valid
    private Airport to;
    @NotBlank
    private String carrier;
    @NotBlank
    private String departureTime;
    @NotBlank
    private String arrivalTime;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Flight(Airport from, Airport to, String carrier, String departureTime, String arrivalTime) {
        this.from = from;
        this.to = to;
        this.carrier = carrier;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Airport getFrom() {
        return from;
    }

    public void setFrom(Airport from) {
        this.from = from;
    }

    public Airport getTo() {
        return to;
    }

    public void setTo(Airport to) {
        this.to = to;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public LocalDateTime getDepartureTime() {
        return LocalDateTime.parse(departureTime, dtf);
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public LocalDateTime getArrivalTime() {
        return LocalDateTime.parse(arrivalTime, dtf);
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
