package io.codelex.flightplanner.service;

import io.codelex.flightplanner.repository.FlightPlannerRepository;
import io.codelex.flightplanner.flight.Flight;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FlightPlannerService {

    private final FlightPlannerRepository flightPlannerRepository;

    public FlightPlannerService(FlightPlannerRepository flightPlannerRepository) {
        this.flightPlannerRepository = flightPlannerRepository;
    }

    public void addFlight(Flight request) {
        if (request.getFrom().equals(request.getTo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Flight can't be to the same place");
        }
        if (!request.getDepartureTime().isBefore(request.getArrivalTime())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Flight can't be arriving before it left");
        }
        if (flightPlannerRepository.listFlights().contains(request)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Flight already exists");
        }
        long id = flightPlannerRepository.listFlights().stream()
                .mapToLong(Flight::getId)
                .max()
                .orElse(0)
                + 1;
        request.setId(id);
        flightPlannerRepository.addFlight(request);
    }

    public Flight getFlight(String stringId) {
        long id = Long.parseLong(stringId);
        List<Flight> flightList = flightPlannerRepository.listFlights().stream()
                .filter(flight -> flight.getId() == id)
                .toList();
        if (flightList.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "List does not contain a flight by this id");
        }
        return flightList.get(0);
    }

    public void removeFlight(String stringId) {
        try {
            Flight foundFlight = getFlight(stringId);
            flightPlannerRepository.removeFlight(foundFlight);
        } catch (Exception e) {

        }
    }

    public void clear() {
        flightPlannerRepository.clear();
    }

}
