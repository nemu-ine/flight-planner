package io.codelex.flightplanner.repository;

import io.codelex.flightplanner.flight.Flight;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightPlannerRepository {

    private final List<Flight> flights = new ArrayList<>();

    public void addFlight(Flight request) {
        flights.add(request);
    }

    public void removeFlight(Flight flight) {
        flights.remove(flight);
    }

    public List<Flight> listFlights() {
        return flights;
    }

    public void clear() {
        flights.clear();
    }

}
