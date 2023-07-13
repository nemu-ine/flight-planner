package io.codelex.flightplanner;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightPlannerRepository {

    private final List<String> test = new ArrayList<>();

    public void clear() {
        test.clear();
    }

}
