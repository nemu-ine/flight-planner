package io.codelex.flightplanner.repository;

import io.codelex.flightplanner.entity.Airport;
import io.codelex.flightplanner.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DatabaseFlightsRepository extends JpaRepository<Flight, Long> {

    @Query(value = """
        SELECT f FROM Flight f
        WHERE
            f.from.airport = :from
        AND
            f.to.airport = :to
        AND
            DATE(f.departureTime) = :departureTime
    """)
    List<Flight> findMatchingFlights(String from, String to, LocalDate departureTime);

    Optional<Flight> findByFromAndToAndCarrierAndDepartureTimeAndArrivalTime(
            Airport from, Airport to, String carrier, LocalDateTime departureTime, LocalDateTime arrivalTime
    );

    void deleteById(Long id);

}
