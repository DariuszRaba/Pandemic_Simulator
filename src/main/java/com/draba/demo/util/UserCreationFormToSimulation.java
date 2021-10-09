package com.draba.demo.util;

import com.draba.demo.model.Population;
import com.draba.demo.model.Simulation;
import com.draba.demo.model.UserCreationForm;

import java.util.stream.Collectors;

public class UserCreationFormToSimulation {

    public Simulation convert(UserCreationForm userCreationForm) {
        return new Simulation(userCreationForm.getName(),
                userCreationForm.getPopulation(),
                userCreationForm.getInfected(),
                userCreationForm.getVirusReproductionRate(),
                userCreationForm.getMortalityRate(),
                userCreationForm.getRecoveryTimeFrame(),
                userCreationForm.getDeathTimeFrame(),
                userCreationForm.getSimulationTime(),
                userCreationForm.getPopulationDTOList().stream()
                        .map(populationDTO -> new PopulationDtoToPopulation().convert(populationDTO))
                        .collect(Collectors.toList()));
    }
}
