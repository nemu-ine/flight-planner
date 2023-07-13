package io.codelex.flightplanner.service;

import io.codelex.flightplanner.repository.FlightPlannerRepository;
import io.codelex.flightplanner.flight.Flight;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightPlannerService {

    private final FlightPlannerRepository flightPlannerRepository;

    public FlightPlannerService(FlightPlannerRepository flightPlannerRepository) {
        this.flightPlannerRepository = flightPlannerRepository;
    }

    public void addFlight(Flight request) {
        List<Flight> existingFlights = flightPlannerRepository.listFlights();
        long id = existingFlights.stream().mapToLong(Flight::getId).max().orElse(0) + 1;
        request.setId(id);
        flightPlannerRepository.addFlight(request);
    }

    public void clear() {
        flightPlannerRepository.clear();
    }

}
