package com.draba.demo.service;

import com.draba.demo.dto.SimulationDTO;
import com.draba.demo.model.Simulation;
import com.draba.demo.model.UserCreationForm;
import com.draba.demo.repository.SimulationRepository;
import com.draba.demo.util.UserCreationFormToSimulation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
class SimulationServiceTest {
    @Mock
    private SimulationRepository simulationRepository;
    @InjectMocks
    private SimulationService simulationService;
    private UserCreationForm userCreationForm;
    private final String json = "{\"name\":\"Simulation Test\",\"population\":10000,\"infected\":10," +
            "\"virusReproductionRate\":0.2,\"mortalityRate\":0.1,\"recoveryTimeFrame\":6,\"deathTimeFrame\":5," +
            "\"simulationTime\":30,\"populationList\":[{\"infected\":10,\"exposedPersons\":9990,\"deadPersons\":0," +
            "\"healers\":0},{\"infected\":12,\"exposedPersons\":9988,\"deadPersons\":0,\"healers\":0},{\"infected\":14," +
            "\"exposedPersons\":9986,\"deadPersons\":0,\"healers\":0},{\"infected\":16,\"exposedPersons\":9984," +
            "\"deadPersons\":0,\"healers\":0},{\"infected\":19,\"exposedPersons\":9981,\"deadPersons\":0," +
            "\"healers\":0},{\"infected\":21,\"exposedPersons\":9978,\"deadPersons\":1,\"healers\":0},{\"infected\":14," +
            "\"exposedPersons\":9974,\"deadPersons\":2,\"healers\":10},{\"infected\":0,\"exposedPersons\":9975," +
            "\"deadPersons\":3,\"healers\":22}]}";
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        this.userCreationForm = getDummyUserForm();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void Check_Simulation_Correctness_Test() throws JsonProcessingException {
        final SimulationDTO simulationDTO = simulationService.makeSimulation(userCreationForm);
        final SimulationDTO simulationDummyContent = getSimulationDtoDummyContent();
        assertEquals(simulationDTO, simulationDummyContent);
    }

    @Test
    public void Save_Simulation_Test() throws JsonProcessingException {
        final UserCreationForm dummyUserForm = getSimulationDummyContent();
        final Simulation simulation = new UserCreationFormToSimulation().convert(dummyUserForm);
        when(simulationRepository.save(simulation)).thenReturn(simulation);

        final Simulation returnedValue = simulationService.saveSimulation(dummyUserForm);
        assertEquals(simulation,returnedValue);
    }
    private SimulationDTO getSimulationDtoDummyContent() throws JsonProcessingException {
        return objectMapper.readValue(json, SimulationDTO.class);
    }

    private UserCreationForm getSimulationDummyContent() throws JsonProcessingException {
        return objectMapper.readValue(json,UserCreationForm.class);
    }


    private UserCreationForm getDummyUserForm() {
        final String simulationName = "Simulation Test";
        final int population = 10_000;
        final int infected = 10;
        final float virusReproductionRate = 0.20f;
        final float mortalityRate = 0.10f;
        final int recoveryTimeFrame = 6;
        final int deathTimeFrame = 5;
        final int simulationTimeFrame = 30;

        return new UserCreationForm(simulationName,
                population,
                infected,
                virusReproductionRate,
                mortalityRate,
                recoveryTimeFrame,
                deathTimeFrame,
                simulationTimeFrame,
                null);
    }
}
