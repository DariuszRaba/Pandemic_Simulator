package com.draba.demo.model;

import lombok.Data;

import java.util.List;

@Data
public class Simulation {
    private String name;
    private int population;
    private int infected;
    private float virusReproductionRate;
    private float mortalityRate;
    private int recoveryTimeFrame;
    private int deathTimeFrame;
    private int simulationTime;
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
}
