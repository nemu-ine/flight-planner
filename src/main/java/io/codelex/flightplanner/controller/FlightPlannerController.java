package io.codelex.flightplanner.controller;

import io.codelex.flightplanner.flight.Airport;
import io.codelex.flightplanner.flight.Flight;
import io.codelex.flightplanner.flight.FlightSearch;
import io.codelex.flightplanner.response.PageResult;
import io.codelex.flightplanner.service.FlightPlannerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightPlannerController {

    private final FlightPlannerService flightPlannerService;

    public FlightPlannerController(FlightPlannerService flightPlannerService) {
        this.flightPlannerService = flightPlannerService;
    }

    @GetMapping("/airports")
    public ResponseEntity<List<Airport>> searchAirports(@RequestParam("search") String request) {
        return ResponseEntity.ok(flightPlannerService.getFilteredAirports(request));
    }

    @PostMapping("/flights/search")
    public ResponseEntity<PageResult<Flight>> searchFlights(@Valid @RequestBody FlightSearch flightSearch) {
        return ResponseEntity.ok(flightPlannerService.getFilteredFlights(flightSearch));
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<Flight> findFlightById(@PathVariable String id) {
        return ResponseEntity.ok(flightPlannerService.getFlight(id))
;    }

}
