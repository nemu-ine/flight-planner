package io.codelex.flightplanner.flight;

import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class FlightSearch {

    @NotBlank
    private String from;
    @NotBlank
    private String to;
    @NotBlank
    private String departureDate;

    public FlightSearch(String from, String to, String departureDate) {
        this.from = from;
        this.to = to;
        this.departureDate = departureDate;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    @Override
    public String toString() {
        return "FlightSearch{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", departureDate='" + departureDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightSearch that = (FlightSearch) o;
        return Objects.equals(from, that.from) && Objects.equals(to, that.to) && Objects.equals(departureDate, that.departureDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, departureDate);
    }
}
