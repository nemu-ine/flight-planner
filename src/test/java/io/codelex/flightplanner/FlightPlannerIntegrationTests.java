package io.codelex.flightplanner;

import io.codelex.flightplanner.controller.AdminController;
import io.codelex.flightplanner.controller.TestingController;
import io.codelex.flightplanner.entity.Airport;
import io.codelex.flightplanner.entity.Flight;
import io.codelex.flightplanner.repository.DatabaseFlightsRepository;
import io.codelex.flightplanner.service.I_FlightPlannerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class FlightPlannerIntegrationTests {

    @Autowired
    AdminController adminController;
    @Autowired
    TestingController testingController;
    @Autowired
    I_FlightPlannerService IFlightPlannerService;
    @Autowired
    DatabaseFlightsRepository databaseFlightsRepository;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    final Flight testFlight = new Flight(
            new Airport("Latvia", "Riga", "RIX"),
            new Airport("Sweden", "Stockholm", "ARN"),
            "Ryanair",
            LocalDateTime.parse("2023-04-04 15:00", dtf),
            LocalDateTime.parse("2023-04-04 18:00", dtf)
    );

    @BeforeEach
    public void setUp() {
        IFlightPlannerService.clear();
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

            assertEquals(IFlightPlannerService.listAllFlights(), 1);
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
            assertEquals(IFlightPlannerService.listAllFlights().size(), 0);
        } catch (Exception ignored) {
        }
    }

}
