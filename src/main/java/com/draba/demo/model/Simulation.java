package com.draba.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Simulation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int population;
    private int infected;
    private float virusReproductionRate;
    private float mortalityRate;
    private int recoveryTimeFrame;
    private int deathTimeFrame;
    private int simulationTime;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Population> populationList;

    public Simulation(String name, int population, int infected, float virusReproductionRate, float mortalityRate, int recoveryTimeFrame, int deathTimeFrame, int simulationTime) {
        this.name = name;
        this.population = population;
        this.infected = infected;
        this.virusReproductionRate = virusReproductionRate;
        this.mortalityRate = mortalityRate;
        this.recoveryTimeFrame = recoveryTimeFrame;
        this.deathTimeFrame = deathTimeFrame;
        this.simulationTime = simulationTime;
    }

    public Simulation(String name, int population, int infected, float virusReproductionRate, float mortalityRate, int recoveryTimeFrame, int deathTimeFrame, int simulationTime, List<Population> populationList) {
        this.name = name;
        this.population = population;
        this.infected = infected;
        this.virusReproductionRate = virusReproductionRate;
        this.mortalityRate = mortalityRate;
        this.recoveryTimeFrame = recoveryTimeFrame;
        this.deathTimeFrame = deathTimeFrame;
        this.simulationTime = simulationTime;
        this.populationList = populationList;
    }
}
