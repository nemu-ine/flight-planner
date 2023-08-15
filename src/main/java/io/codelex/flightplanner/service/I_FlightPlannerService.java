package io.codelex.flightplanner.service;

import io.codelex.flightplanner.entity.Airport;
import io.codelex.flightplanner.entity.Flight;
import io.codelex.flightplanner.dto.FlightSearch;
import io.codelex.flightplanner.response.PageResult;

import java.util.List;

public interface I_FlightPlannerService {

    void addFlight(Flight request);
    Flight getFlight(String stringId);
    void removeFlight(String stringId);
    List<Airport> getFilteredAirports(String request);
    PageResult<Flight> getFilteredFlights(FlightSearch flightSearch);
    void clear();

}
