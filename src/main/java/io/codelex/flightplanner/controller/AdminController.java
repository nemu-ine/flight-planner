package io.codelex.flightplanner.controller;

import io.codelex.flightplanner.service.FlightPlannerService;
import io.codelex.flightplanner.flight.Flight;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin-api")
public class AdminController {

    private final FlightPlannerService flightPlannerService;

    public AdminController(FlightPlannerService flightPlannerService) {
        this.flightPlannerService = flightPlannerService;
    }

    @PutMapping("/flights")
    public ResponseEntity<Flight> addFlight (@Valid @RequestBody Flight request) {
        flightPlannerService.addFlight(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(request);
    }

}
