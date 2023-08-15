package io.codelex.flightplanner.service;

import io.codelex.flightplanner.entity.Airport;
import io.codelex.flightplanner.entity.Flight;
import io.codelex.flightplanner.dto.FlightSearch;
import io.codelex.flightplanner.repository.MemoryFlightPlannerRepository;
import io.codelex.flightplanner.response.PageResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class MemoryIFlightPlannerService implements I_FlightPlannerService {

    private final MemoryFlightPlannerRepository memoryFlightPlannerRepository;

    public MemoryIFlightPlannerService(MemoryFlightPlannerRepository memoryFlightPlannerRepository) {
        this.memoryFlightPlannerRepository = memoryFlightPlannerRepository;
    }

    public synchronized void addFlight(Flight request) {
        if (memoryFlightPlannerRepository.listFlights().contains(request)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Flight already exists");
        }
        if (request.getFrom().equals(request.getTo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Flight can't be to the same place");
        }
        if (!request.getDepartureTime().isBefore(request.getArrivalTime())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Flight can't be arriving before it left");
        }
        long id = memoryFlightPlannerRepository.listFlights().stream()
                .mapToLong(Flight::getId)
                .max()
                .orElse(0)
                + 1;
        request.setId(id);
        memoryFlightPlannerRepository.addFlight(request);
    }

    public Flight getFlight(String stringId) {
        long id = Long.parseLong(stringId);
        List<Flight> flightList = memoryFlightPlannerRepository.listFlights().stream()
                .filter(flight -> flight.getId() == id)
                .toList();
        if (flightList.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "List does not contain a flight by this id");
        }
        return flightList.get(0);
    }

    public synchronized void removeFlight(String stringId) {
        try {
            Flight foundFlight = getFlight(stringId);
            memoryFlightPlannerRepository.removeFlight(foundFlight);
        } catch (Exception ignored) {
        }
    }

    public List<Airport> getFilteredAirports(String request) {
        String formattedRequest = request.toLowerCase().strip();
        return memoryFlightPlannerRepository.listFlights().stream()
                .map(Flight::getFrom)
                .filter(flightFrom -> flightFrom.getAirport().toLowerCase().contains(formattedRequest)
                        || flightFrom.getCity().toLowerCase().contains(formattedRequest)
                        || flightFrom.getCountry().toLowerCase().contains(formattedRequest))
                .toList();
    }

    public synchronized PageResult<Flight> getFilteredFlights(FlightSearch flightSearch) {
        if (flightSearch.getFrom().equalsIgnoreCase(flightSearch.getTo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Flight can't be to the same place");
        }
        List<Flight> filteredFlights = memoryFlightPlannerRepository.listFlights().stream()
                .filter(flight -> flight.getFrom().getAirport().equals(flightSearch.getFrom())
                        && flight.getTo().getAirport().equals(flightSearch.getTo())
                        && flight.getDepartureTime().toString().contains(flightSearch.getDepartureDate().toString()))
                .toList();
        return new PageResult<>(0, filteredFlights.size(), filteredFlights);
    }

    public void clear() {
        memoryFlightPlannerRepository.clear();
    }

}
