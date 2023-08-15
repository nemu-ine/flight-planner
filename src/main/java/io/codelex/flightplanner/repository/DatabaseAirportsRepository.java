package io.codelex.flightplanner.repository;

import io.codelex.flightplanner.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DatabaseAirportsRepository extends JpaRepository<Airport, Long> {

    @Query(value = """
        SELECT a FROM Airport a
        WHERE
            LOWER(a.airport) LIKE %:request%
        OR
            LOWER(a.city) LIKE %:request%
        OR
            LOWER(a.country) LIKE %:request%
    """)
    List<Airport> findMatchingAirports(String request);

    Airport findByAirport(String airport);

}
