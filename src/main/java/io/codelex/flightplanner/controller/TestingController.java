package io.codelex.flightplanner.controller;

import io.codelex.flightplanner.service.I_FlightPlannerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing-api")
public class TestingController {

    private final I_FlightPlannerService IFlightPlannerService;

    public TestingController(I_FlightPlannerService IFlightPlannerService) {
        this.IFlightPlannerService = IFlightPlannerService;
    }

    @PostMapping("/clear")
    public void clearMapping() {
        IFlightPlannerService.clear();
    }

}
