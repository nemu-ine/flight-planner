package io.codelex.flightplanner.repository;

import io.codelex.flightplanner.entity.Flight;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryFlightPlannerRepository {

    private final List<Flight> flights = new ArrayList<>();

    public Flight addFlight(Flight request) {
        flights.add(request);
        return request;
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
