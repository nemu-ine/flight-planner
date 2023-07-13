package io.codelex.flightplanner.controller;

import io.codelex.flightplanner.flight.Airport;
import io.codelex.flightplanner.service.FlightPlannerService;
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
    public ResponseEntity<List<Airport>> search(@RequestParam("search") String request) {
        return ResponseEntity.ok(flightPlannerService.getFilteredAirports(request));
    }


}
