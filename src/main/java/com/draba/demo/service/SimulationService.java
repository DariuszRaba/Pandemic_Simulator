package com.draba.demo.service;

import com.draba.demo.dto.SimulationDTO;
import com.draba.demo.model.Population;
import com.draba.demo.model.Simulation;
import com.draba.demo.model.UserCreationForm;
import com.draba.demo.repository.SimulationRepository;
import com.draba.demo.util.SimulationToSimulationDTO;
import com.draba.demo.util.UserCreationFormToSimulation;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimulationService {

    private final SimulationRepository simulationRepository;


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

    public Simulation saveSimulation(UserCreationForm userCreationForm) {
        return simulationRepository.save(new UserCreationFormToSimulation().convert(userCreationForm));
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


    public void remove(String id) {
        try {
            simulationRepository.deleteById(Long.parseLong(id));
        }catch (EmptyResultDataAccessException e){
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT,"No such simulation",e
            );
        }
    }

    public SimulationDTO getSimulationByID(String id) {
        try {
            final Optional<Simulation> theSimulation = simulationRepository.findById(Long.parseLong(id));
            return new SimulationToSimulationDTO().convert(theSimulation.orElseThrow());
        }catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such simulation");
        }
    }
}
