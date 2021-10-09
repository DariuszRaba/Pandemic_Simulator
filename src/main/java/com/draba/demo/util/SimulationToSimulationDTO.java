package com.draba.demo.util;

import com.draba.demo.dto.SimulationDTO;
import com.draba.demo.model.Simulation;

import java.util.stream.Collectors;

public class SimulationToSimulationDTO {

    public SimulationDTO convert(Simulation simulation) {
        return new SimulationDTO(
                simulation.getName(),
                simulation.getPopulation(),
                simulation.getInfected(),
                simulation.getVirusReproductionRate(),
                simulation.getMortalityRate(),
                simulation.getRecoveryTimeFrame(),
                simulation.getDeathTimeFrame(),
                simulation.getSimulationTime(),
                simulation.getPopulationList().stream()
                        .map(population -> new PopulationToPopulationDTO().convert(population))
                        .collect(Collectors.toList()));
    }
}
