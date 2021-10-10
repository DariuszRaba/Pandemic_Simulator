package com.draba.demo.service;

import com.draba.demo.dto.SimulationDTO;
import com.draba.demo.model.UserCreationForm;
import com.draba.demo.repository.SimulationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class SimulationServiceTest {
    @Mock
    private SimulationRepository simulationRepository;

    private SimulationService simulationService;

    @BeforeEach
    void setUp() {
        this.simulationService = new SimulationService(simulationRepository);
    }

    @Test
    public void Check_Simulation_Correctness() throws JsonProcessingException {
        final String simulationName = "Simulation Test";
        final int population = 10_000;
        final int infected = 10;
        final float virusReproductionRate = 0.20f;
        final float mortalityRate = 0.10f;
        final int recoveryTimeFrame = 6;
        final int deathTimeFrame = 5;
        final int simulationTimeFrame = 30;

        final UserCreationForm creationForm = new UserCreationForm(simulationName,
                population,
                infected,
                virusReproductionRate,
                mortalityRate,
                recoveryTimeFrame,
                deathTimeFrame,
                simulationTimeFrame,
                null);

        final SimulationDTO simulationDTO = simulationService.makeSimulation(creationForm);
        final SimulationDTO simulationDummyContent = getSimulationDummyContent();
        assertEquals(simulationDTO, simulationDummyContent);
    }

    private SimulationDTO getSimulationDummyContent() throws JsonProcessingException {
        final String json = "  {\"name\":\"Simulation Test\",\"population\":10000,\"infected\":10," +
                "\"virusReproductionRate\":0.2,\"mortalityRate\":0.1,\"recoveryTimeFrame\":6,\"deathTimeFrame\":5," +
                "\"simulationTime\":30,\"populationList\":[{\"infected\":10,\"exposedPersons\":9990,\"deadPersons\":0," +
                "\"healers\":0},{\"infected\":12,\"exposedPersons\":9988,\"deadPersons\":0,\"healers\":0},{\"infected\":14," +
                "\"exposedPersons\":9986,\"deadPersons\":0,\"healers\":0},{\"infected\":16,\"exposedPersons\":9984," +
                "\"deadPersons\":0,\"healers\":0},{\"infected\":19,\"exposedPersons\":9981,\"deadPersons\":0," +
                "\"healers\":0},{\"infected\":21,\"exposedPersons\":9978,\"deadPersons\":1,\"healers\":0},{\"infected\":14," +
                "\"exposedPersons\":9974,\"deadPersons\":2,\"healers\":10},{\"infected\":0,\"exposedPersons\":9975," +
                "\"deadPersons\":3,\"healers\":22}]}";
        final ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, SimulationDTO.class);
    }

}
