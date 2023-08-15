package io.codelex.flightplanner.controller;

import io.codelex.flightplanner.entity.Airport;
import io.codelex.flightplanner.entity.Flight;
import io.codelex.flightplanner.dto.FlightSearch;
import io.codelex.flightplanner.response.PageResult;
import io.codelex.flightplanner.service.I_FlightPlannerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightPlannerController {

    private final I_FlightPlannerService IFlightPlannerService;

    public FlightPlannerController(I_FlightPlannerService IFlightPlannerService) {
        this.IFlightPlannerService = IFlightPlannerService;
    }

    @GetMapping("/airports")
    public ResponseEntity<List<Airport>> searchAirports(@RequestParam("search") String request) {
        return ResponseEntity.ok(IFlightPlannerService.getFilteredAirports(request));
    }

    @PostMapping("/flights/search")
    public ResponseEntity<PageResult<Flight>> searchFlights(@Valid @RequestBody FlightSearch flightSearch) {
        return ResponseEntity.ok(IFlightPlannerService.getFilteredFlights(flightSearch));
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<Flight> findFlightById(@PathVariable String id) {
        return ResponseEntity.ok(IFlightPlannerService.getFlight(id));
    }

}
