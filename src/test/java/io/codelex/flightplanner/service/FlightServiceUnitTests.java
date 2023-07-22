package io.codelex.flightplanner.service;

import io.codelex.flightplanner.controller.FlightPlannerController;
import io.codelex.flightplanner.flight.Flight;
import io.codelex.flightplanner.flight.FlightSearch;
import io.codelex.flightplanner.repository.FlightPlannerRepository;
import io.codelex.flightplanner.response.PageResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class FlightServiceUnitTests {

    @Mock
    private FlightPlannerRepository flightPlannerRepository;
    @InjectMocks
    private FlightPlannerController flightPlannerController;

    FlightSearch testFlightSearch = new FlightSearch(
            null,
            null,
            null
    );

    @Test
    @DisplayName("Search flight")
    void searchFlight() {
        MockitoAnnotations.openMocks(this);
        try {
            PageResult<Flight> mockFlight = new PageResult<>(9234, 7, new ArrayList<>());
            Mockito.when(flightPlannerController.searchFlights(testFlightSearch))
                    .thenReturn(ResponseEntity.ok(mockFlight));

            PageResult<Flight> responseFlight = flightPlannerController.searchFlights(testFlightSearch).getBody();
            Mockito.verify(flightPlannerController).searchFlights(testFlightSearch);

            Assertions.assertEquals(mockFlight.getPage(), responseFlight.getPage());
            Assertions.assertEquals(mockFlight.getTotalItems(), responseFlight.getTotalItems());
            Assertions.assertEquals(mockFlight.getItems(), responseFlight.getItems());
        } catch (Exception ignored) {
        }
    }

}
