package com.draba.demo.service;

import com.draba.demo.dto.SimulationDTO;
import com.draba.demo.model.Population;
import com.draba.demo.model.Simulation;
import com.draba.demo.model.UserCreationForm;
import com.draba.demo.repository.SimulationRepository;
import com.draba.demo.util.SimulationToSimulationDTO;
import com.draba.demo.util.UserCreationFormToSimulation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimulationService {

    private SimulationRepository simulationRepository;


    public SimulationDTO makeSimulation(UserCreationForm userCreationForm) {
        final Simulation simulation = new Simulation(userCreationForm.getName(),
                userCreationForm.getPopulation(),
                userCreationForm.getInfected(),
                userCreationForm.getVirusReproductionRate(),
                userCreationForm.getMortalityRate(),
                userCreationForm.getRecoveryTimeFrame(),
                userCreationForm.getDeathTimeFrame(),
                userCreationForm.getSimulationTime());
        simulation.setPopulationList(simulate(simulation));
        return new SimulationToSimulationDTO().convert(simulation);
    }

    public void saveSimulation(UserCreationForm userCreationForm) {
        simulationRepository.save(new UserCreationFormToSimulation().convert(userCreationForm));
    }

    private List<Population> simulate(Simulation simulation) {
        final List<Population> populations = new ArrayList<>();
        int infected = simulation.getInfected();
        int exposedPersons = simulation.getPopulation() - simulation.getInfected();
        int deadPersons = 0;
        int healers = 0;
        populations.add(new Population(infected, exposedPersons, deadPersons, healers));

        for (int day = 1; day < simulation.getSimulationTime(); day++) {
            if (infected == 0) break;
            int newDeathPersons = calculateNewDeathPersons(populations, day, simulation.getDeathTimeFrame(), simulation.getMortalityRate(), infected);
            deadPersons += newDeathPersons;
            healers += calculateNewHealers(populations, day, simulation.getRecoveryTimeFrame(), infected);

            infected += (int) (infected * simulation.getVirusReproductionRate()) - (newDeathPersons + healers);
            if (infected < 0) infected = 0;

            exposedPersons = simulation.getPopulation() - (infected + healers + deadPersons);
            populations.add(new Population(infected, exposedPersons, deadPersons, healers));
        }
        return populations;
    }

    private int calculateNewHealers(List<Population> populations, int day, int recoveryTimeFrame, int infected) {
        if (day >= recoveryTimeFrame) {
            final int potentialHealers = populations.get(day - recoveryTimeFrame).getInfected();
            return Math.min(potentialHealers, infected);
        }
        return 0;
    }

    private int calculateNewDeathPersons(List<Population> populations, int day, int deathTimeFrame, float mortalityRate, int infected) {
        if (day >= deathTimeFrame) {
            int potentialDeadPersons = (int) (populations.get(day - deathTimeFrame).getInfected() * mortalityRate);
            return Math.min(potentialDeadPersons, infected);
        }
        return 0;
    }


}
