package io.codelex.flightplanner.controller;

import io.codelex.flightplanner.entity.Flight;
import io.codelex.flightplanner.service.I_FlightPlannerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin-api")
public class AdminController {

    private final I_FlightPlannerService IFlightPlannerService;

    public AdminController(I_FlightPlannerService IFlightPlannerService) {
        this.IFlightPlannerService = IFlightPlannerService;
    }

    @PutMapping("/flights")
    public ResponseEntity<Flight> addFlight (@Valid @RequestBody Flight request) {
        IFlightPlannerService.addFlight(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(request);
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable String id) {
        return ResponseEntity.ok(IFlightPlannerService.getFlight(id));
    }

    @DeleteMapping("/flights/{id}")
    public void removeFlight(@PathVariable String id) {
        IFlightPlannerService.removeFlight(id);
    }

}
