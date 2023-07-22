package io.codelex.flightplanner;

import io.codelex.flightplanner.controller.AdminController;
import io.codelex.flightplanner.controller.TestingController;
import io.codelex.flightplanner.flight.Airport;
import io.codelex.flightplanner.flight.Flight;
import io.codelex.flightplanner.repository.FlightPlannerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class FlightPlannerIntegrationTests {

    @Autowired
    AdminController adminController;
    @Autowired
    TestingController testingController;
    @Autowired
    FlightPlannerRepository flightPlannerRepository;

    final Flight testFlight = new Flight(
            new Airport("Latvia", "Riga", "RIX"),
            new Airport("Sweden", "Stockholm", "ARN"),
            "Ryanair",
            "2023-04-04 15:00",
            "2023-04-04 18:00"
    );

    @BeforeEach
    public void setUp() {
        flightPlannerRepository.clear();
    }

    @Test
    @DisplayName("Add flight test")
    void addFlight() {
        try {
            Flight response = adminController.addFlight(testFlight).getBody();

            assertNotNull(response.getId());
            assertEquals(response.getFrom(), testFlight.getFrom());
            assertEquals(response.getTo(), testFlight.getTo());
            assertEquals(response.getCarrier(), testFlight.getCarrier());
            assertEquals(response.getDepartureTime(), testFlight.getDepartureTime());
            assertEquals(response.getArrivalTime(), testFlight.getArrivalTime());

            assertEquals(flightPlannerRepository.listFlights().size(), 1);
        } catch (Exception ignored) {
        }
    }

    @Test
    @DisplayName("Clear flights")
    void clearFlights() {
        try {
            adminController.addFlight(testFlight);
            adminController.addFlight(testFlight);

            testingController.clearMapping();
            assertEquals(flightPlannerRepository.listFlights().size(), 0);
        } catch (Exception ignored) {
        }
    }

}
