package io.codelex.flightplanner.config;

import io.codelex.flightplanner.repository.DatabaseAirportsRepository;
import io.codelex.flightplanner.repository.DatabaseFlightsRepository;
import io.codelex.flightplanner.repository.MemoryFlightPlannerRepository;
import io.codelex.flightplanner.service.DatabaseFlightPlannerService;
import io.codelex.flightplanner.service.I_FlightPlannerService;
import io.codelex.flightplanner.service.MemoryFlightPlannerService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "flight-planner", name = "datasource", havingValue = "in-memory")
    public I_FlightPlannerService getMemoryFlightService(MemoryFlightPlannerRepository memoryFlightPlannerRepository) {
        return new MemoryFlightPlannerService(memoryFlightPlannerRepository);
    }

    @Bean
    @ConditionalOnProperty(prefix = "flight-planner", name = "datasource", havingValue = "database")
    public I_FlightPlannerService getDatabaseFlightService(DatabaseFlightsRepository databaseFlightsRepository, DatabaseAirportsRepository databaseAirportsRepository) {
        return new DatabaseFlightPlannerService(databaseFlightsRepository, databaseAirportsRepository);
    }

}
