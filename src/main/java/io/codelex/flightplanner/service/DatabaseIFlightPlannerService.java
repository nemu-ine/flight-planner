package io.codelex.flightplanner.service;

import io.codelex.flightplanner.entity.Airport;
import io.codelex.flightplanner.entity.Flight;
import io.codelex.flightplanner.dto.FlightSearch;
import io.codelex.flightplanner.repository.DatabaseAirportsRepository;
import io.codelex.flightplanner.repository.DatabaseFlightsRepository;
import io.codelex.flightplanner.response.PageResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public class DatabaseIFlightPlannerService implements I_FlightPlannerService {

    private final DatabaseFlightsRepository databaseFlightsRepository;
    private final DatabaseAirportsRepository databaseAirportsRepository;

    public DatabaseIFlightPlannerService(DatabaseFlightsRepository databaseFlightsRepository, DatabaseAirportsRepository databaseAirportsRepository) {
        this.databaseAirportsRepository = databaseAirportsRepository;
        this.databaseFlightsRepository = databaseFlightsRepository;
    }

    public synchronized void addFlight(Flight request) {
        if (request.getFrom().equals(request.getTo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Flight can't be to the same place");
        }
        if (!request.getDepartureTime().isBefore(request.getArrivalTime())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Flight can't be arriving before it left");
        }

        request.setFrom(validateAirport(request.getFrom()));
        request.setTo(validateAirport(request.getTo()));

        Optional<Flight> optionalFlight = databaseFlightsRepository.findByFromAndToAndCarrierAndDepartureTimeAndArrivalTime(
                request.getFrom(), request.getTo(), request.getCarrier(), request.getDepartureTime(), request.getArrivalTime()
        );
        if (optionalFlight.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Flight already exists");
        }

        databaseFlightsRepository.save(request);
    }

    private Airport validateAirport(Airport airport) {
        Airport airportInDatabase = databaseAirportsRepository.findByAirport(airport.getAirport());
        if (airportInDatabase != null) {
            return airportInDatabase;
        }
        return databaseAirportsRepository.save(airport);
    }

    public Flight getFlight(String stringId) {
        Optional<Flight> flight = databaseFlightsRepository.findById(Long.parseLong(stringId));
        if (flight.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Database does not contain a flight by this id");
        }
        return flight.get();
    }

    public synchronized void removeFlight(String stringId) {
        databaseFlightsRepository.deleteById(Long.parseLong(stringId));
    }

    public List<Airport> getFilteredAirports(String request) {
        return databaseAirportsRepository.findMatchingAirports(request.toLowerCase().strip());
    }

    public synchronized PageResult<Flight> getFilteredFlights(FlightSearch flightSearch) {
        if (flightSearch.getFrom().equals(flightSearch.getTo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Flight can't be to the same place");
        }
        List<Flight> flights = databaseFlightsRepository.findMatchingFlights(flightSearch.getFrom(), flightSearch.getTo(), flightSearch.getDepartureDate());
        return new PageResult<>(0, flights.size(), flights);
    }

    public void clear() {
        databaseAirportsRepository.deleteAll();
        databaseFlightsRepository.deleteAll();
    }

}
