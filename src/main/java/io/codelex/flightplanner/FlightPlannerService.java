package io.codelex.flightplanner;

import org.springframework.stereotype.Service;

@Service
public class FlightPlannerService {

    private final FlightPlannerRepository flightPlannerRepository;

    public FlightPlannerService(FlightPlannerRepository flightPlannerRepository) {
        this.flightPlannerRepository = flightPlannerRepository;
    }

    public void clear() {
        flightPlannerRepository.clear();
    }

}
